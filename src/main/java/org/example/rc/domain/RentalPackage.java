package org.example.rc.domain;

import javax.persistence.*;

@Entity
@Table(name = "rental_package")
public class RentalPackage {
    @Id
    @Column(name = "code", length = 2)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
