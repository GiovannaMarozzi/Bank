package bank.count.api.accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByNumber(Long number);
}
