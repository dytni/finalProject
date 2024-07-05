package by.dytni.finalshop.service;

import by.dytni.finalshop.domain.users.User;
import by.dytni.finalshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final UserRepository userRepository;
    public User getProductById(Integer id) {
    Optional<User> product = userRepository.findById(id);
    return product.orElse(null);
}
}
