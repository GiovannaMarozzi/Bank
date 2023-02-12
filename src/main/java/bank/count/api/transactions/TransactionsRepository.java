package bank.count.api.transactions;

import bank.count.api.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface TransactionsRepository extends JpaRepository <Transactions, Long> {
    List<Transactions> findByNumber(Long number);

}
