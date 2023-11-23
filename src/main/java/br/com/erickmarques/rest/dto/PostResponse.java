package br.com.erickmarques.rest.dto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    private String postText;
    private LocalDateTime dateCreate;
}
