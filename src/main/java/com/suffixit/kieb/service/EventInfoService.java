package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.EventCreationRequest;
import com.suffixit.kieb.dto.EventInfoDTO;
import com.suffixit.kieb.dto.EventInfoModel;
import com.suffixit.kieb.dto.UpdateEventInfoModel;
import com.suffixit.kieb.entities.EventInfo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EventInfoService {
    String createEventInfo(EventCreationRequest infoModel) throws IOException;

    Page<EventInfoDTO> getAll(Integer page , Integer size);
    Optional<EventInfoDTO> getById(Integer eventId);

    List<EventInfoDTO> getByCategoryId(Integer catId);

    String updateEventInfo(UpdateEventInfoModel eventInfoModel) throws IOException;


    ResponseEntity<InputStreamResource> getImageForEvent(String eventCategory, String imageName);

}
