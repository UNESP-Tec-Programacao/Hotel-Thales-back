package br.com.unesp.Thales_Hotel.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Table(name = "reserve")
@Entity
@Getter @Setter
@NoArgsConstructor
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentWay paymentWay;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private Instant startsAt;

    @Column(nullable = false)
    private Instant endsAt;

    public enum PaymentWay {
        CREDIT_CARD,
        DEBIT_CARD,
        PIX,
        CASH
    }

    public Reserve(Customer customer, Room room, Double price, PaymentWay paymentWay, Integer number, Instant startsAt, Instant endsAt) {
        this.customer = customer;
        this.room = room;
        this.price = price;
        this.paymentWay = paymentWay;
        this.number = number;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }
}
