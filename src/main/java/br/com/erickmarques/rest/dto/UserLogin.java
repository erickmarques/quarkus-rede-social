package br.com.erickmarques.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLogin {

    @NotNull(message = "O campo e-mail é obrigatório!")
    @Email(message = "Favor informar um e-mail válido!")
    private String email;

    @NotNull(message = "O campo senha é obrigatório!")
    private String password;
}
