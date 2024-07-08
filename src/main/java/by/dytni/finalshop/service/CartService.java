package by.dytni.finalshop.service;


import by.dytni.finalshop.domain.cart.Cart;
import by.dytni.finalshop.domain.product.Product;
import by.dytni.finalshop.domain.users.User;
import by.dytni.finalshop.repository.CartRepository;
import by.dytni.finalshop.repository.ProductRepository;
import by.dytni.finalshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void addProductToCart(Integer productId) throws Exception {
        Integer userId = userService.getCurrentUserId();

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new Exception("User not found");
        }
        User user = optionalUser.get();

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new Exception("Product not found");
        }
        Product product = optionalProduct.get();

        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }

        cart.getProducts().add(product);

        cartRepository.save(cart);
        userRepository.save(user);
    }

    @Transactional
    public void removeProductFromCart(Integer productId) throws Exception {
        Integer userId = userService.getCurrentUserId();

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new Exception("User not found");
        }
        User user = optionalUser.get();

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new Exception("Product not found");
        }
        Product product = optionalProduct.get();

        Cart cart = user.getCart();
        if (cart == null) {
            throw new Exception("Cart not found for user");
        }

        if (!cart.getProducts().remove(product)) {
            throw new Exception("Product not found in cart");
        }

        cartRepository.save(cart);
    }
}
