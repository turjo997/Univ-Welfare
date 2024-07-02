package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.IebSlug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IebSlugRepository extends JpaRepository<IebSlug , Integer> {
}
