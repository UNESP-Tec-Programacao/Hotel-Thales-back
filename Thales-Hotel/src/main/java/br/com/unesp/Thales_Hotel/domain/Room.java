package br.com.unesp.Thales_Hotel.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "rooms")
@Entity
@Getter @Setter
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer floor;

    @Column(nullable = false)
    private Integer number;

    public Room(String nome, String category, Double price, Integer floor, Integer number) {
        this.name = nome;
        this.category = category;
        this.price = price;
        this.floor = floor;
        this.number = number;
    }
}
