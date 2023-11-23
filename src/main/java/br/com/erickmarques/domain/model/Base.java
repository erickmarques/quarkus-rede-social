package br.com.erickmarques.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Base implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Column(name = "date_change")
    private LocalDateTime dateChange;

    @PrePersist
    public void prePersist(){
        setDateCreate(LocalDateTime.now());
    }

}
