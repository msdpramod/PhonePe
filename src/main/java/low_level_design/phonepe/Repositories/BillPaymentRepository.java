package low_level_design.phonepe.Repositories;

import low_level_design.phonepe.Models.BillPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillPaymentRepository extends JpaRepository<BillPayment, UUID> {}
