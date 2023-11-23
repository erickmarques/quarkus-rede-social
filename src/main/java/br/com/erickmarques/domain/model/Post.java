package br.com.erickmarques.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post extends Base {

    @Column(name = "post_text")
    private String postText;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
