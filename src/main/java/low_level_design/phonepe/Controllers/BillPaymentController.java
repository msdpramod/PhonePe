package low_level_design.phonepe.Controllers;

import low_level_design.phonepe.DTOs.BillPaymentDTO;
import low_level_design.phonepe.Exceptions.InsufficientBalanceException;
import low_level_design.phonepe.Exceptions.UserNotFoundException;
import low_level_design.phonepe.Service.BillPaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/bills")
public class BillPaymentController {

    private final BillPaymentService billPaymentService;

    public BillPaymentController(BillPaymentService billPaymentService) {
        this.billPaymentService = billPaymentService;
    }

    @PostMapping("/pay")
    public ResponseEntity<String> payBill(@RequestBody BillPaymentDTO billPaymentDTO) throws UserNotFoundException, InsufficientBalanceException {
        String response = billPaymentService.payBill(billPaymentDTO);
        return ResponseEntity.ok(response);
    }
}

