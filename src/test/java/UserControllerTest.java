
import by.dytni.finalshop.controller.UserController;
import by.dytni.finalshop.domain.users.User;
import by.dytni.finalshop.domain.cart.Cart;
import by.dytni.finalshop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    // Мокаем объект Cart
    @Mock
    private Cart mockCart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
   // @WithMockUser(roles = "ADMIN") // Симулируем, что запрос приходит от пользователя с ролью ADMIN
    void testSaveUser() throws Exception {
        // Создаем пользователя с моком корзины
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("ROLE_USER");
        user.setCart(mockCart); // Присваиваем мока корзины

        // Мокаем поведение userService
        doNothing().when(userService).saveUser(user);

        mockMvc.perform(post("/user/create")
                        .flashAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(view().name("user")); // Ожидаем, что будет возвращена вьюха "user"

        // Проверяем, что сервис сохранения пользователя был вызван
        verify(userService, times(1)).saveUser(user);
    }

    @Test
    void testSignUpUserWithMismatchedPasswords() throws Exception {
        mockMvc.perform(post("/user/create/u")
                        .param("username", "testuser")
                        .param("password", "password1")
                        .param("confirmPassword", "password2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/signup"))
                .andExpect(flash().attribute("error", "Passwords do not match!"));
    }

    @Test
  //  @WithMockUser(roles = "ADMIN")   // Симулируем роль администратора
    void testShowUsers() throws Exception {
        // Создаем список пользователей с моком корзины
        List<User> users = Arrays.asList(
                new User(1 ,"testuser1", "password", "ROLE_USER", mockCart),
                new User(2, "testuser2", "password", "ROLE_USER", mockCart)
        );

        when(userService.getUsers()).thenReturn(users);

        mockMvc.perform(get("/user/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attribute("users", users));

        verify(userService, times(1)).getUsers();
    }

    @Test
    //@WithMockUser(roles = "USER") // Симулируем роль пользователя
    void testUserInfo() throws Exception {
        User user = new User(3 ,"testuser", "password", "ROLE_USER", mockCart);
        user.setId(1);
        when(userService.getCurrentUserId()).thenReturn(1);
        when(userService.getUser(1)).thenReturn(user);

        mockMvc.perform(get("/user/info"))
                .andExpect(status().isOk())
                .andExpect(view().name("user"))
                .andExpect(model().attribute("user", user));

        verify(userService, times(1)).getUser(1);
    }

    @Test
    //@WithMockUser(roles = "USER") // Симулируем роль пользователя
    void testUserInfoRedirectIfNotLoggedIn() throws Exception {
        when(userService.getCurrentUserId()).thenReturn(null);

        mockMvc.perform(get("/user/info"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void testSignUp() throws Exception {
        mockMvc.perform(get("/user/signup"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/signup"));
    }
}
