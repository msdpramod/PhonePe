package low_level_design.phonepe.Controllers;

import low_level_design.phonepe.DTOs.UserDTO;
import low_level_design.phonepe.DTOs.UserLoginDTO;
import low_level_design.phonepe.Exceptions.UserNotFoundException;
import low_level_design.phonepe.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO registeredUser = userService.registerUser(userDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO loginDTO) {
        String response = userService.loginUser(loginDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestParam UUID userId) throws UserNotFoundException {
        String response = userService.logoutUser(userId);
        return ResponseEntity.ok(response);
    }
}

