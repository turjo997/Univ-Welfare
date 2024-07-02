package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.AlbumNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumNewsRepository extends JpaRepository<AlbumNews , Integer> {
}
