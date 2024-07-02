package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.EventCategory;
import com.suffixit.kieb.entities.EventInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventInfoRepository extends JpaRepository<EventInfo , Integer> {
  Optional<EventInfo> findByEventTitle(String eventTitle);

  List<EventInfo> findAllByEventCategoryOrderByEventTitle( EventCategory eventCategory);
}
