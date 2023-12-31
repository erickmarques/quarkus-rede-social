package br.com.erickmarques.rest.dto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApplicationScoped
public class UserRequest {

    @NotEmpty(message = "O campo nome é obrigatório!")
    private String name;

    @NotNull(message = "O campo data de nascimento é obrigatório!")
    private LocalDateTime birthday;

    @NotNull(message = "O campo e-mail é obrigatório!")
    @Email(message = "Favor informar um e-mail válido!")
    private String email;

    @NotNull(message = "O campo senha é obrigatório!")
    private String password;
}
