package br.com.unesp.Thales_Hotel.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Table(name = "login")
@Entity
@Getter @Setter
@NoArgsConstructor
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private UUID loggedUser;

    @Column(nullable = false)
    private Instant initialDate;

    @Column(nullable = false)
    private Instant expirationDate;

    public Login(UUID loggedUser, Instant initialDate, Instant expirationDate) {
        this.loggedUser = loggedUser;
        this.initialDate = initialDate;
        this.expirationDate = expirationDate;
    }
}
