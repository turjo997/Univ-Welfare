package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.CommitteeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitteeCategoryRepository extends JpaRepository<CommitteeCategory, Integer> {

    boolean existsByCategoryName(String categoryName);

    boolean existsByCategoryShortName(String categoryShortName);


}
