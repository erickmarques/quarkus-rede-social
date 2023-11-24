package br.com.erickmarques.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private LocalDateTime birthday;
    private String email;
}
