package by.dytni.finalshop.config;

import by.dytni.finalshop.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig /* extends WebSecurityConfigurerAdapter */{

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserService();
    }
    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/products", "/products/info/**", "/login/**", "/user/create/u", "/user/signup", "/swagger-ui/**").permitAll()
                        .requestMatchers("/products/**").authenticated()
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/products", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                .build();
    }
    @Bean
public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

   /* protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyMatchers("/").permitAll() // Разрешаем доступ всем на главную страницу
                .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                .and()
                .formLogin()
                .loginPage("/login") // Указываем URL страницы логина
                .permitAll() // Разрешаем доступ всем к странице логина
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // URL при успешном выходе
                .permitAll(); // Разрешаем доступ всем к URL выхода
    }*/
}
