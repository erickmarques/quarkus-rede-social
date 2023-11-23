package br.com.erickmarques.rest.dto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@ApplicationScoped
public class FollowerRequest {

    @NotNull(message = "Favor informar o seguidor!")
    private Long follewerId;
}
