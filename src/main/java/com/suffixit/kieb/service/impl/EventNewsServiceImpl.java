package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.EventInfoDTO;
import com.suffixit.kieb.dto.EventNewsDto;
import com.suffixit.kieb.entities.EventInfo;
import com.suffixit.kieb.entities.EventNews;
import com.suffixit.kieb.entities.NewsInfo;
import com.suffixit.kieb.repository.EventInfoRepository;
import com.suffixit.kieb.repository.EventNewsRepository;
import com.suffixit.kieb.repository.NewsInfoRepository;
import com.suffixit.kieb.service.EventNewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class EventNewsServiceImpl implements EventNewsService {
    private final EventNewsRepository eventNewsRepository;
    private final EventInfoRepository eventInfoRepository;
    private final NewsInfoRepository newsInfoRepository;

    public EventNewsServiceImpl(EventNewsRepository eventNewsRepository, EventInfoRepository eventInfoRepository, NewsInfoRepository newsInfoRepository) {
        this.eventNewsRepository = eventNewsRepository;
        this.eventInfoRepository = eventInfoRepository;
        this.newsInfoRepository = newsInfoRepository;
    }

    @Override
    public String createNewsEvent(EventNewsDto eventNewsDto) {
        NewsInfo newsInfo = newsInfoRepository.findById(eventNewsDto.getNewsId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"news info id not found" + eventNewsDto.getNewsId()));
        EventInfo eventInfo = eventInfoRepository.findById(eventNewsDto.getEventId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"event info id not found"+eventNewsDto.getEventId()));
        Optional<EventNews>optional=eventNewsRepository.findByNewsInfoIdAndEvent_Id(newsInfo.getId(), eventInfo.getId());
        if(optional.isPresent()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"duplicate data ");
        }
        EventNews eventNews = new EventNews();
        eventNews.setEvent(eventInfo);
        eventNews.setNewsInfo(newsInfo);
        eventNewsRepository.save(eventNews);
        return "create news and events successfully";
    }

    @Override
    public String updateEventsNews(EventNewsDto eventNewsDto) {
        EventNews eventNews=eventNewsRepository.findById((eventNewsDto.getId())).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"eventsNews id not found "));
        NewsInfo newsInfo = newsInfoRepository.findById(eventNewsDto.getNewsId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"news info id not found" + eventNewsDto.getNewsId()));
        EventInfo eventInfo = eventInfoRepository.findById(eventNewsDto.getEventId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"event info id not found"+eventNewsDto.getEventId()));
        eventNews.setEvent(eventInfo);
        eventNews.setNewsInfo(newsInfo);
        eventNewsRepository.save(eventNews);
        return "successfully updated events-news";
    }


    @Override
    public Page<EventNewsDto> getAll(Pageable pageable) {
       Page<EventNews>eventNews= eventNewsRepository.findAll(pageable);
       return eventNews.map(this::convert);
    }

    @Override
    public EventNews getById(Integer enId) {
        return eventNewsRepository.findById(enId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"event-news id not found"));
    }

    private EventNewsDto convert(EventNews eventNews){
        EventNewsDto eventNewsDto=new EventNewsDto();
        eventNewsDto.setId(eventNews.getId());
        eventNewsDto.setEventId(eventNews.getEvent().getId());
        eventNewsDto.setNewsId(eventNews.getNewsInfo().getId());
        return eventNewsDto;
    }


}
