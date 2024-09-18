package low_level_design.phonepe.Client;

import low_level_design.phonepe.DTOs.SendMoneyRequestDTO;
import low_level_design.phonepe.DTOs.TransactionDTO;
import low_level_design.phonepe.DTOs.UserDTO;
import low_level_design.phonepe.DTOs.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
public class PhonePeClient {

    private final RestTemplate restTemplate;

    @Autowired
    public PhonePeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> sendMoney(SendMoneyRequestDTO request) {
        return restTemplate.postForEntity("http://localhost:8080/api/v1/transaction/send", request, String.class);
    }

    public ResponseEntity<TransactionDTO> getTransactionDetails(UUID transactionId) {
        return restTemplate.getForEntity("http://localhost:8080/api/v1/transaction/" + transactionId, TransactionDTO.class);
    }

    public ResponseEntity<List> getTransactionHistory(UUID userId) {
        return restTemplate.getForEntity("http://localhost:8080/api/v1/transaction/history/" + userId, List.class);
    }

    public ResponseEntity<UserDTO> registerUser(UserDTO userDTO) {
        return restTemplate.postForEntity("http://localhost:8080/api/v1/user/register", userDTO, UserDTO.class);
    }

    public ResponseEntity<String> loginUser(String phoneNumber, String otp) {
        UserLoginDTO loginDTO = new UserLoginDTO();
        loginDTO.setPhoneNumber(phoneNumber);
        loginDTO.setOtp(otp);
        return restTemplate.postForEntity("http://localhost:8080/api/v1/user/login", loginDTO, String.class);
    }

    public ResponseEntity<String> logoutUser(UUID userId) {
        return restTemplate.postForEntity("http://localhost:8080/api/v1/user/logout?userId=" + userId, null, String.class);
    }
}