package by.dytni.finalshop.controller;


import by.dytni.finalshop.domain.users.User;
import by.dytni.finalshop.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/create")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String saveUser(@ModelAttribute User user, Model model) {
        userService.saveUser(user);
        model.addAttribute("users", user);
        return "user";
    }

    @GetMapping("/user/show")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("user/info")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userInfo(Model model) {
        Integer id = userService.getCurrentUserId();
        if (id == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("user", userService.getUser(id));
            return "user";
        }

    }
}
