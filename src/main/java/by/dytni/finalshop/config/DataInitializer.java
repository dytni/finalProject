package by.dytni.finalshop.config;

import by.dytni.finalshop.domain.users.User;
import by.dytni.finalshop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            String username = "admin";
            String password = "admin";
            String role = "ROLE_ADMIN";

            if (userRepository.findByUsername(username).isEmpty()) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                User user = new User();
                user.setUsername(username);
                user.setPassword(encoder.encode(password));
                user.setRole(role);
                userRepository.save(user);
            }
        };
    }
}
