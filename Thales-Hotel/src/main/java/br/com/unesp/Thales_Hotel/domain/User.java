package br.com.unesp.Thales_Hotel.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 70)
    private String name;
    @Column(nullable = false, length = 3)
    private Integer age;
    @Email(message = "Invalid mail format")
    @Column(nullable = false, unique = true)
    private String mail;
    @Column(length = 14)
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.PERSIST)
    private UserAddress userAddress;

}
