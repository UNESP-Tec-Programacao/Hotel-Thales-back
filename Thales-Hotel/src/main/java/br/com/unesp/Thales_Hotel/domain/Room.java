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

    @Column(nullable = false)
    private Integer guestNumber;

    public Room(String name, String category, Double price, Integer floor, Integer number, Integer guestNumber) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.floor = floor;
        this.number = number;
        this.guestNumber = guestNumber;
    }
}
