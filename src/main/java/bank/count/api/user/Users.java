package bank.count.api.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity(name = "Users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "number_account")
public class Users implements UserDetails {

    @Id
    @Column(name = "number_account", nullable = false)
    private Integer number = (int) ((Math.random()*10000000) +1);

    @NotBlank
    private String nome;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

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

    public void transfer(Float value, Float balanceAccount){
        this.saldo = balanceAccount - value;
    }

    public void transferAccount(Float value, Float balanceAccountForTransfer){
        this.saldo = balanceAccountForTransfer + value;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
