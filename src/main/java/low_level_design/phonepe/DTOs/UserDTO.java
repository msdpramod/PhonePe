package low_level_design.phonepe.DTOs;


import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class UserDTO {
    private UUID userId;
    private String phoneNumber;
    private String name;
    private BigDecimal walletBalance;
}