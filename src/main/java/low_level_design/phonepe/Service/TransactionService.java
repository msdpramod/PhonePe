package low_level_design.phonepe.Service;

import low_level_design.phonepe.DTOs.TransactionDTO;
import low_level_design.phonepe.Exceptions.UserNotFoundException;
import low_level_design.phonepe.Models.Transaction;
import low_level_design.phonepe.Models.User;
import low_level_design.phonepe.Repositories.TransactionRepository;
import low_level_design.phonepe.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<TransactionDTO> getTransactionHistory(UUID userId) throws UserNotFoundException {
        logger.debug("Attempting to find user with ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        logger.debug("User found: {}", user);

        logger.debug("Fetching transactions for user ID: {}", userId);
        List<Transaction> transactions = transactionRepository.findBySenderOrReceiver(user, user);
        logger.debug("Found {} transactions for user ID: {}", transactions.size(), userId);

        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .toList();
    }
}