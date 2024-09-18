package low_level_design.phonepe.DTOs;

import lombok.Getter;
import lombok.Setter;
import low_level_design.phonepe.Models.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TransactionDTO {
    private UUID transactionId;
    private UUID senderId;
    private UUID receiverId;
    private BigDecimal amount;
    private LocalDateTime transactionTime;
    private TransactionStatus status;
}