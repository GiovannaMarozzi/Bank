package bank.count.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    UserDetails findByLogin(String login);
    List<Users> findByNumber(Long number);
}
