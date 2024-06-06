package org.example.rc.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class CarRatingTest {

    @Test
    void testConstructorAndGetters() {
        Car car = new Car();
        car.setId(1L);
        User customer = new User();
        customer.setId(1L);

        CarRating carRating = new CarRating();
        carRating.setId(1L);
        carRating.setCar(car);
        carRating.setCustomer(customer);
        carRating.setScore(5);
        carRating.setComment("Excellent car!");

        assertThat(carRating.getId(), is(1L));
        assertThat(carRating.getCar(), is(car));
        assertThat(carRating.getCustomer(), is(customer));
        assertThat(carRating.getScore(), is(5));
        assertThat(carRating.getComment(), is("Excellent car!"));
    }

    @Test
    void testEqualsAndHashCode() {
        Car car1 = new Car();
        car1.setId(1L);
        User customer1 = new User();
        customer1.setId(1L);

        CarRating carRating1 = new CarRating();
        carRating1.setId(1L);
        carRating1.setCar(car1);
        carRating1.setCustomer(customer1);
        carRating1.setScore(5);
        carRating1.setComment("Excellent car!");

        Car car2 = new Car();
        car2.setId(1L);
        User customer2 = new User();
        customer2.setId(1L);

        CarRating carRating2 = new CarRating();
        carRating2.setId(1L);
        carRating2.setCar(car2);
        carRating2.setCustomer(customer2);
        carRating2.setScore(5);
        carRating2.setComment("Excellent car!");

        assertThat(carRating1, is(carRating2));
        assertThat(carRating1.hashCode(), is(carRating2.hashCode()));
    }
}
