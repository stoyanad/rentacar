package org.example.rc.service;

import org.example.rc.dto.CarRatingDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test which uses sample data script
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class CarRatingServiceIntegrationTest {

    private static final Long CUSTOMER_ID = 101L;
    private static final Long CAR_ID = 1L;
    private static final Long NOT_A_CAR_ID = 123L;
    private static final Long NOT_A_CUSTOMER_ID = 999L;

    @Autowired
    private CarRatingService service;

    @Test
    void testFindAll() {
        List<CarRatingDTO> carRatings = service.findAll();
        assertFalse(carRatings.isEmpty());
    }

    @Test
    void testFindById() {
        Optional<CarRatingDTO> carRating = service.findById(1L);
        assertTrue(carRating.isPresent());
        assertThat(carRating.get().getCarId(), is(4L));
    }

    @Test
    void testSave() {
        CarRatingDTO carRatingDTO = new CarRatingDTO();
        carRatingDTO.setCarId(CAR_ID);
        carRatingDTO.setCustomerId(CUSTOMER_ID);
        carRatingDTO.setScore(3);
        carRatingDTO.setComment("It was average");

        CarRatingDTO savedCarRating = service.save(carRatingDTO);
        assertThat(savedCarRating.getCarId(), is(CAR_ID));
        assertThat(savedCarRating.getCustomerId(), is(CUSTOMER_ID));
        assertThat(savedCarRating.getScore(), is(3));
        assertThat(savedCarRating.getComment(), is("It was average"));
    }

    @Test
    void testDeleteById() {
        List<CarRatingDTO> carRatings = service.findAll();
        Long idToDelete = carRatings.get(0).getId();
        service.deleteById(idToDelete);
        assertThat(service.findById(idToDelete).isPresent(), is(false));
    }

    @Test
    void testSaveNonExistingCar() {
        CarRatingDTO carRatingDTO = new CarRatingDTO();
        carRatingDTO.setCarId(NOT_A_CAR_ID);
        carRatingDTO.setCustomerId(CUSTOMER_ID);
        carRatingDTO.setScore(3);
        carRatingDTO.setComment("Non-existing car");

        assertThrows(IllegalArgumentException.class, () -> service.save(carRatingDTO));
    }

    @Test
    void testSaveNonExistingCustomer() {
        CarRatingDTO carRatingDTO = new CarRatingDTO();
        carRatingDTO.setCarId(CAR_ID);
        carRatingDTO.setCustomerId(NOT_A_CUSTOMER_ID);
        carRatingDTO.setScore(3);
        carRatingDTO.setComment("Non-existing customer");

        assertThrows(IllegalArgumentException.class, () -> service.save(carRatingDTO));
    }
}
