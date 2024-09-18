package low_level_design.phonepe.Service;

import low_level_design.phonepe.DTOs.BillPaymentDTO;
import low_level_design.phonepe.Exceptions.InsufficientBalanceException;
import low_level_design.phonepe.Exceptions.UserNotFoundException;
import low_level_design.phonepe.Models.BillPayment;
import low_level_design.phonepe.Models.User;
import low_level_design.phonepe.Repositories.BillPaymentRepository;
import low_level_design.phonepe.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillPaymentService {

    private final BillPaymentRepository billPaymentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public BillPaymentService(BillPaymentRepository billPaymentRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.billPaymentRepository = billPaymentRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public String payBill(BillPaymentDTO billPaymentDTO) throws UserNotFoundException, InsufficientBalanceException {
        try {
            User user = userRepository.findById(billPaymentDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            if (user.getWalletBalance().compareTo(billPaymentDTO.getAmount()) < 0) {
                throw new InsufficientBalanceException("Insufficient balance!");
            }

            user.setWalletBalance(user.getWalletBalance().subtract(billPaymentDTO.getAmount()));
            BillPayment billPayment = modelMapper.map(billPaymentDTO, BillPayment.class);
            billPayment.setUser(user);

            billPaymentRepository.save(billPayment);
            userRepository.save(user);

            return "Bill payment successful!";
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new RuntimeException("Concurrent update error. Please try again.");
        }
    }
}