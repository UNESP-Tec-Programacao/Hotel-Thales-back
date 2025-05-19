package br.com.unesp.Thales_Hotel.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "customer")
@Entity
@Getter @Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 14)
    private String identify;

    @Column(nullable = false, length = 11)
    private String phoneNumber;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false, length = 3)
    private Integer age;

    public Customer(String name, String identify, String phoneNumber, String mail, Integer age) {
        this.name = name;
        this.identify = identify;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.age = age;
    }
}
