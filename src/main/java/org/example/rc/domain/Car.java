package org.example.rc.domain;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "car")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;

    @Column(name = "price_per_day", nullable = false)
    private double pricePerDay;

    @Column(name = "price_per_week", nullable = false)
    private double pricePerWeek;

    @Column(name = "price_per_month", nullable = false)
    private double pricePerMonth;

    @Column(name = "price_per_year", nullable = false)
    private double pricePerYear;

    @Column(name = "availability", nullable = false)
    private boolean availability = true;

    @Column(name = "keywords")
    private String keywords;
}
