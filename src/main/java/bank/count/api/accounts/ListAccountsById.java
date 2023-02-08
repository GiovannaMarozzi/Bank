package bank.count.api.accounts;

public record ListAccountsById(String number, String name, String email, String cpf, String rg, Float saldo) {
    public ListAccountsById(AccountById account){
        this(String.valueOf(account.getNumber()), account.getNome(), account.getRg(), account.getCpf(), account.getCel(), account.getSaldo());
    }
}
