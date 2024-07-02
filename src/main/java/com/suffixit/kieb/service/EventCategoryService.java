package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.EventCategoryModel;
import com.suffixit.kieb.entities.EventCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface EventCategoryService {
    String createEventCategory(EventCategoryModel eventCategoryModel);

    Page<EventCategory> getAll(Pageable  pageable);

    EventCategory getByCatId(int catId);

    String updateEventCategory(EventCategoryModel eventCategoryModel);
}
