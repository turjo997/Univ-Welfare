package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.EventCategoryModel;
import com.suffixit.kieb.entities.EventCategory;
import com.suffixit.kieb.repository.EventCategoryRepository;
import com.suffixit.kieb.service.EventCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class EventCategoryServiceImpl implements EventCategoryService {
    private EventCategoryRepository eventCategoryRepository;

    public EventCategoryServiceImpl(EventCategoryRepository eventCategoryRepository) {
        this.eventCategoryRepository = eventCategoryRepository;
    }

    @Override
    public String createEventCategory(EventCategoryModel model) {
    Optional<EventCategory> optionalEventCategory=eventCategoryRepository.findByCategoryName(model.getCategoryName());
    if(optionalEventCategory.isPresent()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"category title already exists");
    }
        EventCategory eventCategory=new EventCategory();
        eventCategory.setCategoryName(model.getCategoryName());
        eventCategory.setCategoryShortName(model.getCategoryShortName());
        eventCategoryRepository.save(eventCategory);

    return "event category create successfully";
    }

    @Override
    public Page<EventCategory> getAll(Pageable pageable) {
        return eventCategoryRepository.findAll(pageable);
    }

    @Override
    public EventCategory getByCatId(int catId) {
        return eventCategoryRepository.findById(catId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"given category id not found"));
    }

    @Override
    public String updateEventCategory(EventCategoryModel model) {
        EventCategory eventCategory=eventCategoryRepository.findById(model.getId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"category not found"));
        eventCategory.setCategoryName(model.getCategoryName());
        eventCategory.setCategoryShortName(model.getCategoryShortName());
        eventCategoryRepository.save(eventCategory);
        return "updated";
    }
}
