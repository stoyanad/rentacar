package org.example.rc.domain;

import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class CarTest {
    @Test
    void testConstructorAndGetters() {
        Car car = new Car();
        car.setId(1L);
        car.setMake("Toyota");
        car.setModel("Corolla");
        car.setYear(2020);
        car.setColor("White");
        car.setLicensePlate("ABC123");
        car.setPricePerDay(30.00);
        car.setPricePerWeek(200.00);
        car.setPricePerMonth(800.00);
        car.setPricePerYear(9000.00);
        car.setAvailability(true);
        car.setKeywords("Economy, Compact, Reliable");

        assertThat(car.getId(), is(1L));
        assertThat(car.getMake(), is("Toyota"));
        assertThat(car.getModel(), is("Corolla"));
        assertThat(car.getYear(), is(2020));
        assertThat(car.getColor(), is("White"));
        assertThat(car.getLicensePlate(), is("ABC123"));
        assertThat(car.getPricePerDay(), is(30.00));
        assertThat(car.getPricePerWeek(), is(200.00));
        assertThat(car.getPricePerMonth(), is(800.00));
        assertThat(car.getPricePerYear(), is(9000.00));
        assertThat(car.isAvailability(), is(true));
        assertThat(car.getKeywords(), is("Economy, Compact, Reliable"));
    }

    @Test
    void testEqualsAndHashCode() {
        Car car1 = new Car();
        car1.setId(1L);
        car1.setMake("Toyota");
        car1.setModel("Corolla");
        car1.setYear(2020);
        car1.setColor("White");
        car1.setLicensePlate("ABC123");
        car1.setPricePerDay(30.00);
        car1.setPricePerWeek(200.00);
        car1.setPricePerMonth(800.00);
        car1.setPricePerYear(9000.00);
        car1.setAvailability(true);
        car1.setKeywords("Economy, Compact, Reliable");

        Car car2 = new Car();
        car2.setId(1L);
        car2.setMake("Toyota");
        car2.setModel("Corolla");
        car2.setYear(2020);
        car2.setColor("White");
        car2.setLicensePlate("ABC123");
        car2.setPricePerDay(30.00);
        car2.setPricePerWeek(200.00);
        car2.setPricePerMonth(800.00);
        car2.setPricePerYear(9000.00);
        car2.setAvailability(true);
        car2.setKeywords("Economy, Compact, Reliable");

        assertThat(car1, is(car2));
        assertThat(car1.hashCode(), is(car2.hashCode()));
    }
}
