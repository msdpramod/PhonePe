package low_level_design.phonepe.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Transaction {

    @Id
    private UUID transactionId = UUID.randomUUID();

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    private BigDecimal amount;

    private LocalDateTime transactionTime = LocalDateTime.now();
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
}