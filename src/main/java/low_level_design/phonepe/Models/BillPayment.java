package low_level_design.phonepe.Models;

import jakarta.persistence.Entity;
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
public class BillPayment {

    @Id
    private UUID billPaymentId = UUID.randomUUID();
    private String serviceProvider;
    private BigDecimal amount;
    private LocalDateTime paymentTime = LocalDateTime.now();

    @ManyToOne
    private User user;
}

