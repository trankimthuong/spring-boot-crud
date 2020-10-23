package com.codegym.city.repository;

import com.codegym.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICityRepository extends JpaRepository<City, Long> {
    Iterable<City> findCitiesByNameContains(String name);
}
