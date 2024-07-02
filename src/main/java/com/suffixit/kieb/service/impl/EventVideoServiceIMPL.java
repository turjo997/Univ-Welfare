package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.EventVideoDto;
import com.suffixit.kieb.entities.EventInfo;
import com.suffixit.kieb.entities.EventVideo;
import com.suffixit.kieb.entities.VideoGalleryInfo;
import com.suffixit.kieb.repository.EventInfoRepository;
import com.suffixit.kieb.repository.EventVideoRepository;
import com.suffixit.kieb.repository.VideoGalleryRepository;
import com.suffixit.kieb.service.EventVideoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventVideoServiceIMPL implements EventVideoService {
    private final EventVideoRepository eventVideoRepository;
    private final EventInfoRepository eventInfoRepository;
    private final VideoGalleryRepository videoGalleryRepository;

    public EventVideoServiceIMPL(EventVideoRepository eventVideoRepository, EventInfoRepository eventInfoRepository, VideoGalleryRepository videoGalleryRepository) {
        this.eventVideoRepository = eventVideoRepository;
        this.eventInfoRepository = eventInfoRepository;
        this.videoGalleryRepository = videoGalleryRepository;
    }

    @Override
    public Page<EventVideoDto> getAllEventsVideos(Pageable pageable) {
        Page<EventVideo> eventVideos = eventVideoRepository.findAll(pageable);
        if (eventVideos.isEmpty()) {
            return Page.empty();
        }
        return eventVideos.map(this::newDto);
    }

    @Override
    public String postEventvideos(EventVideoDto eventVideoDto) {
        EventInfo info = eventInfoRepository.findById(eventVideoDto.getEventId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "event id not found"));
        VideoGalleryInfo video = videoGalleryRepository.findById(eventVideoDto.getVideoGalleryId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "video id not found"));
        EventVideo ev = new EventVideo();
        ev.setEvent(info);
        ev.setVideo(video);
        eventVideoRepository.save(ev);
        return "event video created ...";
    }

    @Override
    public String update(EventVideoDto eventVideoDto) {
        return null;
    }

    @Override
    public EventVideo getById(Integer evId) {
        return null;
    }


    private EventVideoDto newDto(EventVideo ev) {
        EventVideoDto dto = new EventVideoDto();
        dto.setEventId(ev.getId());
        dto.setEventId(ev.getEvent().getId());
        dto.setVideoGalleryId(ev.getVideo().getId());
        return dto;
    }
}
