package by.dytni.finalshop.service;


import by.dytni.finalshop.domain.users.User;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    public void addAdmin(User user){
        user.setRole("Admin");

    }
}
