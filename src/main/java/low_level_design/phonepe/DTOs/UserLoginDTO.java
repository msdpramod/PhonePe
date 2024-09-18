package low_level_design.phonepe.DTOs;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String phoneNumber;
    private String otp;
}
