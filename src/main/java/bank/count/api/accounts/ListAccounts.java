package bank.count.api.accounts;

import bank.count.api.transactions.Transactions;
import bank.count.api.user.Users;

import java.util.Random;

public record ListAccounts(Integer number, String name, String email, String cpf, String rg, Float saldo) {
    public ListAccounts(Users users){
        this(users.getNumber(), users.getNome(), users.getLogin(), users.getCpf(), users.getRg(), users.getSaldo());
    }
}
