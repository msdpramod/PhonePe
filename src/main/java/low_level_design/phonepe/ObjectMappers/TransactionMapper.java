package low_level_design.phonepe.ObjectMappers;

import low_level_design.phonepe.DTOs.TransactionDTO;
import low_level_design.phonepe.Models.Transaction;

public class TransactionMapper {

    public static TransactionDTO toDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(transaction.getTransactionId());
        transactionDTO.setSenderId(transaction.getSender().getUserId());
        transactionDTO.setReceiverId(transaction.getReceiver().getUserId());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setTransactionTime(transaction.getTransactionTime());
        transactionDTO.setStatus(transaction.getStatus());
        return transactionDTO;
    }

    public static Transaction toEntity(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionDTO.getTransactionId());
        // Assuming you have methods to fetch User entities by their IDs
        // transaction.setSender(userService.getUserById(transactionDTO.getSenderId()));
        // transaction.setReceiver(userService.getUserById(transactionDTO.getReceiverId()));
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTransactionTime(transactionDTO.getTransactionTime());
        transaction.setStatus(transactionDTO.getStatus());
        return transaction;
    }
}