package br.com.erickmarques.rest.dto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@ApplicationScoped
public class PostRequest {

    @NotEmpty(message = "A descrição do post é obrigatório!")
    private String postText;
}
