package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory,Integer> {
    Optional<EventCategory> findByCategoryName(String categoryName);
}
