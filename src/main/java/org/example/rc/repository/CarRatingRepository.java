package org.example.rc.repository;

import org.example.rc.domain.CarRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRatingRepository extends JpaRepository<CarRating, Long> {
}
