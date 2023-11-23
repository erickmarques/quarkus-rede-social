package br.com.erickmarques.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class FollowersPerUserResponse {
    private Integer follewersCount;
    private List<FollowerResponse> content;
}
