package by.dytni.finalshop.repository;


import by.dytni.finalshop.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
