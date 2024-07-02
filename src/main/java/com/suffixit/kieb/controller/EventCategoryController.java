package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.EventCategoryModel;
import com.suffixit.kieb.entities.EventCategory;
import com.suffixit.kieb.service.EventCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-category")
public class EventCategoryController {
    private final EventCategoryService eventCategoryService;

    public EventCategoryController(EventCategoryService eventCategoryService) {
        this.eventCategoryService = eventCategoryService;
    }

    @PostMapping("/admin/create")
    public ResponseEntity<String>createEventCategory(@RequestBody EventCategoryModel eventCategoryModel){
        return ResponseEntity.ok( eventCategoryService.createEventCategory(eventCategoryModel));

    }

    @GetMapping("/getAll")
    public Page<EventCategory>getAll(@RequestParam(defaultValue = "0")int pageNo,@RequestParam(defaultValue = "10")int size){
        Pageable pageable=PageRequest.of(pageNo, size);
        return eventCategoryService.getAll(pageable);
    }
    @GetMapping("/admin/getById")
    public ResponseEntity<EventCategory>getByCatId(@RequestParam int catId){
        return ResponseEntity.ok(eventCategoryService.getByCatId(catId));
    }
    @PutMapping("/admin/update-eventCategory")
    public ResponseEntity<String>updateEventCategory(@RequestBody EventCategoryModel eventCategoryModel){
        return ResponseEntity.ok( eventCategoryService.updateEventCategory(eventCategoryModel));

    }

}
