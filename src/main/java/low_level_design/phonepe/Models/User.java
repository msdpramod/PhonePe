package low_level_design.phonepe.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
public class User {

    @Id
    private UUID userId = UUID.randomUUID();
    private String name;
    private String phoneNumber;
    private String otp;
    private boolean isLoggedIn;
    private BigDecimal walletBalance;
}
