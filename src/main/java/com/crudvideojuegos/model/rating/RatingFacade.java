package com.crudvideojuegos.model.rating;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RatingFacade extends JpaRepository<Rating, Long> {
}