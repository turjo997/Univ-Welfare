package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.AlbumEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumEventRepository extends JpaRepository<AlbumEvent , Integer> {
}
