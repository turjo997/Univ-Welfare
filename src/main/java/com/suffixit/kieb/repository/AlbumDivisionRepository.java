package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.AlbumDivision;
import com.suffixit.kieb.entities.MemberDivision;
import com.suffixit.kieb.entities.PictureCategory;
import com.suffixit.kieb.entities.UniversitySubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumDivisionRepository extends JpaRepository<AlbumDivision , Integer> {

    List<AlbumDivision> findByAlbumId(Integer albumId);

    //boolean existsByAlbumAndSubject(PictureCategory pictureCategory, UniversitySubject subject);

}
