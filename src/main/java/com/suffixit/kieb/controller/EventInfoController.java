package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.EventCreationRequest;
import com.suffixit.kieb.dto.EventInfoDTO;

import com.suffixit.kieb.dto.UpdateEventInfoModel;
import com.suffixit.kieb.service.EventInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/event-info")
public class EventInfoController {
    private final EventInfoService eventInfoService;

    public EventInfoController(EventInfoService eventInfoService) {
        this.eventInfoService = eventInfoService;

    }

    @PostMapping("/admin/create")
    public ResponseEntity<String> createEventInfo( EventCreationRequest infoModel) throws IOException {

        return ResponseEntity.ok(eventInfoService.createEventInfo(infoModel));
    }





    @GetMapping("/getBy-category-Id")

    public List<EventInfoDTO> getByCategoryId(@RequestParam Integer catId) {

        return eventInfoService.getByCategoryId(catId);
    }


    @PutMapping("/admin/update-eventInfo")
    public ResponseEntity<String> updateEventInfo( UpdateEventInfoModel eventInfoModel) throws IOException {
        return ResponseEntity.ok(eventInfoService.updateEventInfo(eventInfoModel));
    }



}
