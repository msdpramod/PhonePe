package low_level_design.phonepe.Service;

import low_level_design.phonepe.DTOs.UserDTO;
import low_level_design.phonepe.DTOs.UserLoginDTO;
import low_level_design.phonepe.Exceptions.CustomException;
import low_level_design.phonepe.Exceptions.UserNotFoundException;
import low_level_design.phonepe.Models.User;
import low_level_design.phonepe.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDTO registerUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public String loginUser(UserLoginDTO loginDTO) {
        User user = userRepository.findByPhoneNumber(loginDTO.getPhoneNumber());
        if (user != null && user.getOtp().equals(loginDTO.getOtp())) {
            user.setLoggedIn(true);
            userRepository.save(user);
            return "Login successful!";
        }
        throw new CustomException("Invalid OTP or phone number");
    }

    public String logoutUser(UUID userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setLoggedIn(false);
        userRepository.save(user);
        return "Logout successful!";
    }
}

