package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.AlbumCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumCenterRepository extends JpaRepository<AlbumCenter , Integer> {
}
