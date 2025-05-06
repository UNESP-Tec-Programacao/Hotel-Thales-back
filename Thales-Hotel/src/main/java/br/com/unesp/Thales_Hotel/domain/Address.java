package br.com.unesp.Thales_Hotel.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter @Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 9)
    private String cep;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String neighborhood;
    @Column(nullable = false)
    private String city;
    @Column(length = 2, nullable = false)
    private String state;
    @Column(nullable = false)
    private String country;

    public Address(String cep, String street, String neighborhood, String city, String state, String country) {
        this.cep = cep;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.country = country;
    }
}
