package bank.count.api.accounts;

import java.util.Random;

public record ListAccounts(Double number, String name, String email, String cpf, String rg, Float saldo) {
    public ListAccounts(Account account){
        this(account.getNumber(), account.getNome(), account.getRg(), account.getCpf(), account.getCel(), account.getSaldo());
    }
}
