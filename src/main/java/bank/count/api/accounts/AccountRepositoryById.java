package bank.count.api.accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.Set;

public interface AccountRepositoryById extends JpaRepository<AccountById, Long> {
}
