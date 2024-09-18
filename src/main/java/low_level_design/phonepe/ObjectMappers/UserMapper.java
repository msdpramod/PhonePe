package low_level_design.phonepe.ObjectMappers;

import low_level_design.phonepe.DTOs.UserDTO;
import low_level_design.phonepe.Models.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setName(user.getName());
        userDTO.setWalletBalance(user.getWalletBalance());
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setName(userDTO.getName());
        user.setWalletBalance(userDTO.getWalletBalance());
        return user;
    }
}