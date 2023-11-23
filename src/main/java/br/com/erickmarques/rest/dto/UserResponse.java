package br.com.erickmarques.rest.dto;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private Integer age;
}
