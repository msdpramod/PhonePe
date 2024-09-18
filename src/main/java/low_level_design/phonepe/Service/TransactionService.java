package low_level_design.phonepe.Service;

import low_level_design.phonepe.DTOs.TransactionDTO;
import low_level_design.phonepe.Exceptions.InsufficientBalanceException;
import low_level_design.phonepe.Exceptions.UserNotFoundException;
import low_level_design.phonepe.Models.Transaction;
import low_level_design.phonepe.Models.User;
import low_level_design.phonepe.Repositories.TransactionRepository;
import low_level_design.phonepe.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

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
        User sender = userRepository.findById(transactionDTO.getSenderId())
                .orElseThrow(() -> new UserNotFoundException("Sender not found"));
        User receiver = userRepository.findById(transactionDTO.getReceiverId())
                .orElseThrow(() -> new UserNotFoundException("Receiver not found"));

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

        return "Transaction successful!";
    }

    public List<TransactionDTO> getTransactionHistory(UUID userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        List<Transaction> transactions = transactionRepository.findBySenderOrReceiver(user, user);
        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .toList();
    }
}
