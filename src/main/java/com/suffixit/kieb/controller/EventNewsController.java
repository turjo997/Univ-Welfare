package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.EventNewsDto;
import com.suffixit.kieb.entities.EventNews;
import com.suffixit.kieb.service.EventNewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news-event")
public class EventNewsController {
    private final EventNewsService eventNewsService;

    public EventNewsController(EventNewsService eventNewsService) {
        this.eventNewsService = eventNewsService;
    }

    @PostMapping("/admin/create")
    public ResponseEntity<String>createNewsEvent(@RequestBody EventNewsDto eventNewsDto){
        return ResponseEntity.ok(eventNewsService.createNewsEvent(eventNewsDto));

    }

    @GetMapping("/get-by")
    public EventNews getById(@RequestParam Integer enId){
        return eventNewsService.getById(enId);
    }


}
