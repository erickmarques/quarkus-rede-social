package br.com.erickmarques.rest.dto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@ApplicationScoped
public class UserRequest {

    @NotEmpty(message = "O campo nome é obrigatório!")
    private String name;

    @NotNull(message = "O campo idade é obrigatório!")
    private Integer age;
}
