package bank.count.api.transactions;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    @Column(name = "CPF", nullable = false)
    private String cpf;

    @NotBlank
    @Pattern(regexp = "\\d{9}")
    @Column(name = "RG", nullable = false)
    private String rg;

    @NotBlank
    @Column(name = "date", nullable = false)
    private String date_transaction;

    @NotNull
    @Column(name = "transaction_value", nullable = false)
    private Float value;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;
}
