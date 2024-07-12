package by.dytni.finalshop.controller;

import by.dytni.finalshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Integer id) throws Exception {
        service.addProductToCart(id);
        return "redirect:/products";
    }

    @PostMapping("/cart/delete/{id}")
    public String deleteFromCart(@PathVariable Integer id) throws Exception {
        service.removeProductFromCart(id);
        return "redirect:/user/info";
    }
}
