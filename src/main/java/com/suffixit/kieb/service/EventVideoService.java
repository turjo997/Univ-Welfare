package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.EventVideoDto;
import com.suffixit.kieb.entities.EventVideo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventVideoService {
    Page<EventVideoDto> getAllEventsVideos(Pageable pageable);

    String postEventvideos(EventVideoDto eventVideoDto);

    String update(EventVideoDto eventVideoDto);

    EventVideo getById(Integer evId);
}
