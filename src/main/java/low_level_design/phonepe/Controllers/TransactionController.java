package low_level_design.phonepe.Controllers;

import low_level_design.phonepe.DTOs.TransactionDTO;
import low_level_design.phonepe.Exceptions.InsufficientBalanceException;
import low_level_design.phonepe.Exceptions.UserNotFoundException;
import low_level_design.phonepe.Service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMoney(@RequestBody TransactionDTO transactionDTO) throws UserNotFoundException, InsufficientBalanceException {
        String response = transactionService.sendMoney(transactionDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionHistory(@PathVariable UUID userId) throws UserNotFoundException {
        List<TransactionDTO> transactions = transactionService.getTransactionHistory(userId);
        return ResponseEntity.ok(transactions);
    }
}
