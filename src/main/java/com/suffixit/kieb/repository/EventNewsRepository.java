package com.suffixit.kieb.repository;


import com.suffixit.kieb.entities.EventInfo;
import com.suffixit.kieb.entities.EventNews;
import com.suffixit.kieb.entities.NewsInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventNewsRepository extends JpaRepository<EventNews,Integer> {
    Page<EventNews>findAllByNewsInfo(NewsInfo newsInfo, Pageable pageable);
    Optional<EventNews> findByNewsInfoIdAndEvent_Id(Integer newsId,Integer eventId);
}
