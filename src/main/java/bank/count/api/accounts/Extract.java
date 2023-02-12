package bank.count.api.accounts;

import bank.count.api.transactions.Transactions;
import bank.count.api.user.Users;

public record Extract(Long number, String date, Float value, String type) {

    public Extract(Transactions transactions) {
        this(transactions.getNumber(), transactions.getDate_transaction(), transactions.getValue(), transactions.getType());
    }
}
