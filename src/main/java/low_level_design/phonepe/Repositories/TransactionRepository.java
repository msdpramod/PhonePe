package low_level_design.phonepe.Repositories;


import low_level_design.phonepe.Models.Transaction;
import low_level_design.phonepe.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findBySenderOrReceiver(User sender, User receiver);

}