package low_level_design.phonepe.DTOs;



import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class SendMoneyRequestDTO {
    private UUID senderId;
    private UUID receiverId;
    private BigDecimal amount;
}