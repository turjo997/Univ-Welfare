package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.ConvertNewsInfoDTO;
import com.suffixit.kieb.dto.NewsInfoDTO;
import com.suffixit.kieb.dto.UpdateNewsInfoDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Optional;

public interface NewsInfoService {
    String createNewsInfo(NewsInfoDTO infoDTO) throws IOException;

    Page<ConvertNewsInfoDTO> getAll(Integer pageNo , Integer size);

    Optional<ConvertNewsInfoDTO> getById(Integer newsId);

    Page<ConvertNewsInfoDTO> getByCategoryId(Integer catId,Pageable pageable);

    String updateNewsInfo(UpdateNewsInfoDTO updateNewsInfoDTO) throws IOException;

    ResponseEntity<InputStreamResource> getImageForNews(String newsCategory, String imageName);

}
