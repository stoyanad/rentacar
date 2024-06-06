package org.example.rc.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "car_rating")
@Data
public class CarRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @Column(nullable = false)
    private Integer score;

    @Column()
    private String comment;

}
