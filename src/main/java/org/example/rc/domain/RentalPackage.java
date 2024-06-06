package org.example.rc.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rental_package")
@Data
public class RentalPackage {
    @Id
    @Column(name = "code", length = 2)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;
}
