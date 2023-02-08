package bank.count.api.accounts;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Account{

//        @GeneratedValue(strategy = GenerationType.AUTO)
//        private Long id;
        @Id
        @Column(name = "number_account", nullable = false)
        private Integer number = (int) ((Math.random()*10000000) +1);

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

        public void deposit(Float value, Float balance){
            this.saldo = balance + value;
        }

        public void withdraw(Float value, Float balance){
                this.saldo = balance - value;
        }

}
