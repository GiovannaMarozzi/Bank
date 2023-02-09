package bank.count.api.transactions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository <Transactions, Long> {
}
