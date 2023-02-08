package bank.count.api.accounts;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
public class Account {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(name = "number_account", nullable = false)
        int number;

        @NotBlank
        private String nome;

        @NotBlank
        @Email
        @Column(name = "email", nullable = false)
        private String email;

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        @Column(name = "cpf", nullable = false)
        private String cpf;

        @NotBlank
        @Pattern(regexp = "\\d{9}")
        @Column(name = "rg", nullable = false)
        private String rg;

        @NotBlank
        @Column(name = "cel", nullable = false)
        private String cel;

        @NotNull
        @Column(name = "saldo", nullable = false)
        private Float saldo;
}
