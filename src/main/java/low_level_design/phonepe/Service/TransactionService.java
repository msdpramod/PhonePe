package low_level_design.phonepe.Service;

import low_level_design.phonepe.DTOs.TransactionDTO;
import low_level_design.phonepe.Exceptions.InsufficientBalanceException;
import low_level_design.phonepe.Exceptions.UserNotFoundException;
import low_level_design.phonepe.Models.Transaction;
import low_level_design.phonepe.Models.User;
import low_level_design.phonepe.Repositories.TransactionRepository;
import low_level_design.phonepe.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
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

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public String sendMoney(TransactionDTO transactionDTO) throws UserNotFoundException, InsufficientBalanceException {
        try {
            logger.debug("Attempting to find sender with ID: {}", transactionDTO.getSenderId());
            User sender = userRepository.findById(transactionDTO.getSenderId())
                    .orElseThrow(() -> new UserNotFoundException("Sender not found"));

            logger.debug("Attempting to find receiver with ID: {}", transactionDTO.getReceiverId());
            User receiver = userRepository.findById(transactionDTO.getReceiverId())
                    .orElseThrow(() -> new UserNotFoundException("Receiver not found"));

            logger.debug("Sender found: {}", sender);
            logger.debug("Receiver found: {}", receiver);

            if (sender.getWalletBalance().compareTo(transactionDTO.getAmount()) < 0) {
                throw new InsufficientBalanceException("Insufficient balance!");
            }

            sender.setWalletBalance(sender.getWalletBalance().subtract(transactionDTO.getAmount()));
            receiver.setWalletBalance(receiver.getWalletBalance().add(transactionDTO.getAmount()));

            Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
            transaction.setSender(sender);
            transaction.setReceiver(receiver);

            transactionRepository.save(transaction);
            userRepository.save(sender);
            userRepository.save(receiver);

            logger.debug("Transaction successful for sender ID: {}", transactionDTO.getSenderId());
            return "Transaction successful!";
        } catch (ObjectOptimisticLockingFailureException e) {
            logger.error("Concurrent update error for sender ID: {}", transactionDTO.getSenderId(), e);
            throw new RuntimeException("Concurrent update error. Please try again.");
        }
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