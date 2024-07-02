package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.EventVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventVideoRepository extends JpaRepository<EventVideo,Integer> {
}
