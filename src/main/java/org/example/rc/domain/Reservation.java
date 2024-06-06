package org.example.rc.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "rental_package_code", nullable = false)
    private RentalPackage rentalPackage;

    @Column(name = "reservation_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date reservationDate;

    @Column(name = "rental_start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date rentalStartDate;

    @Column(name = "rental_end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date rentalEndDate;

}
