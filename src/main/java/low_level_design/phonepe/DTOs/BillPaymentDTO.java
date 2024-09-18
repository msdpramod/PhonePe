package low_level_design.phonepe.DTOs;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class BillPaymentDTO {
    private UUID userId;
    private String serviceProvider;
    private BigDecimal amount;
}

