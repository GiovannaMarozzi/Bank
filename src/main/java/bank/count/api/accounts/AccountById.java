package bank.count.api.accounts;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="account")
@Entity(name="accountById")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "number")

public class AccountById {
    private Long id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private String nome;
    private String cpf;
    private String rg;
    private String cel;
    private Float saldo;

}
