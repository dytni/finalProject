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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String saveUser(@ModelAttribute User user, Model model) {
        userService.saveUser(user);
        model.addAttribute("users", user);
        return "user";
    }

    @PostMapping("/user/create/u")
    public String signUpUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            RedirectAttributes redirectAttributes) {

        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match!");
            return "redirect:/user/signup";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("ROLE_USER");

        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/user/show")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("user/signup")
    public String signUp() {
        return "signup";
    }



    @GetMapping("user/info")
    public String userInfo(Model model) {
        Integer id = userService.getCurrentUserId();
        if (id == null) {
            return "redirect:/login";
        } else {
            User user = userService.getUser(id);
            model.addAttribute("user", user);
            if (user.getRole().equals("ROLE_ADMIN") || user.getRole().equals("ROLE_ADMIN, ROLE_USER")) {
                return "admin";
            } else
                return "user";
        }

    }
}
