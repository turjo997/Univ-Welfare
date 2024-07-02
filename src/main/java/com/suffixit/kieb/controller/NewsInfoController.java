package com.suffixit.kieb.controller;


import com.suffixit.kieb.dto.NewsInfoDTO;
import com.suffixit.kieb.dto.UpdateNewsInfoDTO;
import com.suffixit.kieb.service.NewsInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/news-info")
public class NewsInfoController {
    private final NewsInfoService newsInfoService;

    public NewsInfoController(NewsInfoService newsInfoService) {
        this.newsInfoService = newsInfoService;
    }


    @PostMapping("/admin/create")
    public ResponseEntity<String> createNewsInfo(NewsInfoDTO infoDTO) throws IOException {
        return ResponseEntity.ok(newsInfoService.createNewsInfo(infoDTO));
    }




    @PutMapping("/admin/update-newsInfo")
    public ResponseEntity<String> updateNewsInfo(UpdateNewsInfoDTO updateNewsInfoDTO) throws IOException {
        return ResponseEntity.ok(newsInfoService.updateNewsInfo(updateNewsInfoDTO));
    }
}
