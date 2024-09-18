package low_level_design.phonepe.Repositories;

import low_level_design.phonepe.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByPhoneNumber(String phoneNumber);

}