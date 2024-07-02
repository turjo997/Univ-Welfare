package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.EventVideoDto;
import com.suffixit.kieb.entities.EventVideo;
import com.suffixit.kieb.service.EventVideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-video")
public class EventVideoController {
    private final EventVideoService eventVideoService;

    public EventVideoController(EventVideoService eventVideoService) {
        this.eventVideoService = eventVideoService;
    }

    @PostMapping("/admin/post")
    public ResponseEntity<String>postEventvideos(@RequestBody EventVideoDto eventVideoDto){
        return ResponseEntity.ok(eventVideoService.postEventvideos(eventVideoDto));
    }

    @PutMapping("/admin/update")
    public ResponseEntity<String>updateEventVideos(@RequestBody EventVideoDto eventVideoDto){
        return ResponseEntity.ok(eventVideoService.update(eventVideoDto));
    }
    @GetMapping("/getBy-Id")
    public ResponseEntity<EventVideo>getById(@RequestParam Integer evId){
        return ResponseEntity.ok(eventVideoService.getById(evId));
    }
}
