package uz.pdp.clickuplesson8tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.RegisterDTO;
import uz.pdp.clickuplesson8tasks.entity.User;
import uz.pdp.clickuplesson8tasks.entity.enums.SystemRoleName;
import uz.pdp.clickuplesson8tasks.repository.UserRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JavaMailSender javaMailSender;

    public ApiResponse registerUser(RegisterDTO registerDTO) {

        final boolean existsByEmail = userRepository.existsByEmail(registerDTO.getEmail());
        if (existsByEmail) {
            return new ApiResponse("User already exists", false);
        }
        User user = new User(
                registerDTO.getFullName(),
                registerDTO.getEmail(),
                passwordEncoder.encode(registerDTO.getPassword()),
                SystemRoleName.USER
        );
        final int code = new Random().nextInt(9999999);

        user.setEmailCode(String.valueOf(code).substring(0, 4));
        userRepository.save(user);

        sendEmail(user.getEmail(), user.getEmailCode());
        return new ApiResponse("User saved!", true);
    }

    public void sendEmail(String sendingEmail, String emailCode) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("test@pdp.uz");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Account verification");
            mailMessage.setText(emailCode);
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ApiResponse verifyEmail(String email, String code) {
        final Optional<User> optionalUser = userRepository.findAllByEmail(email);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }
        final User user = optionalUser.get();
        if (code.equals(user.getEmailCode())) {
            user.setEnabled(true);
            userRepository.save(user);
            return new ApiResponse("Account verified!", true);
        }
        return new ApiResponse("Code do not match", false);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findAllByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
