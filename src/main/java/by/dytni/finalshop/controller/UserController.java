package by.dytni.finalshop.controller;

import by.dytni.finalshop.domain.product.Product;
import by.dytni.finalshop.domain.users.User;
import by.dytni.finalshop.repository.UserRepository;
import by.dytni.finalshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/user/create")
    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String saveUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/products";
    }
//    String userLogin
}
