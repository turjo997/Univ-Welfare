package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.EventNewsDto;
import com.suffixit.kieb.entities.EventNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventNewsService {
    String createNewsEvent(EventNewsDto eventNewsDto);

String updateEventsNews(EventNewsDto eventNewsDto);
    Page<EventNewsDto> getAll(Pageable pageable);

    EventNews getById(Integer enId);
}
