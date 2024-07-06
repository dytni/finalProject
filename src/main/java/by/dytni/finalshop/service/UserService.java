package by.dytni.finalshop.service;

import by.dytni.finalshop.config.UserDetailsImpl;
import by.dytni.finalshop.domain.users.User;
import by.dytni.finalshop.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(UserDetailsImpl::new).orElseThrow(()-> new UsernameNotFoundException(username + " not found"));
    }
    public void saveUser(User user){
        userRepository.save(user);
    }
}
