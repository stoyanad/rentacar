package org.example.rc.dto;

import lombok.Data;

@Data
public class CarDTO {
    private Long id;
    private String make;
    private String model;
    private int year;
    private String color;
    private String licensePlate;
    private double pricePerDay;
    private double pricePerWeek;
    private double pricePerMonth;
    private double pricePerYear;
    private boolean availability;
    private String keywords;

}
