package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.ConvertNewsInfoDTO;
import com.suffixit.kieb.dto.EventInfoDTO;
import com.suffixit.kieb.dto.ImageInfo;
import com.suffixit.kieb.dto.PictureDTO;
import com.suffixit.kieb.service.CommitteeService;
import com.suffixit.kieb.service.EventInfoService;
import com.suffixit.kieb.service.NewsInfoService;
import com.suffixit.kieb.service.PictureCategoryService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class OpenResourceController {

    private final PictureCategoryService pictureCategoryService;
    private final EventInfoService eventInfoService;

    private final NewsInfoService newsInfoService;

    private final CommitteeService committeeService;

    public OpenResourceController(PictureCategoryService pictureCategoryService, EventInfoService eventInfoService, NewsInfoService newsInfoService, CommitteeService committeeService) {
        this.pictureCategoryService = pictureCategoryService;
        this.eventInfoService = eventInfoService;
        this.newsInfoService = newsInfoService;
        this.committeeService = committeeService;
    }

    @GetMapping("/album/first-image")
    public List<ImageInfo> ImagePathByCategory() {
        return pictureCategoryService.ImagePathByCategory();
    }

    @GetMapping("/image/{imageCategory}/{pictureName}")
    public ResponseEntity<InputStreamResource> getFirstImageForCategory(@PathVariable("imageCategory") String imageCategory, @PathVariable("pictureName") String pictureName) throws FileNotFoundException {
        return pictureCategoryService.getAllImagesForCategory(imageCategory, pictureName);
    }

    @GetMapping("/get/all/image/{albumId}")
    public List<PictureDTO> getAllPicturesByAlbum(@PathVariable("albumId") Integer albumId) {
        return pictureCategoryService.getAllPicturesByAlbum(albumId);
    }


    @GetMapping("/get/all/events/info")
    public Page<EventInfoDTO> getAllEvents(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int size) {
        return eventInfoService.getAll(pageNo, size);
    }

    @GetMapping("/event-image/{eventCategory}/{imageName}")
    public ResponseEntity<InputStreamResource> getImageForEvent(@PathVariable("eventCategory") String eventCategory, @PathVariable("imageName") String imageName) {

        return eventInfoService.getImageForEvent(eventCategory, imageName);
    }

    @GetMapping("/getEventBy-category-Id")

    public List<EventInfoDTO> getByCategoryId(@RequestParam Integer catId) {

        return eventInfoService.getByCategoryId(catId);
    }

    @GetMapping("/event/getById")
    public ResponseEntity<Optional<EventInfoDTO>> getByEventId(@RequestParam Integer eventId) {
        return ResponseEntity.ok(eventInfoService.getById(eventId));
    }

    @GetMapping("/get/all/news/info")
    public Page<ConvertNewsInfoDTO> getAllNews(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int size) {
        return newsInfoService.getAll(pageNo, size);
    }

    @GetMapping("/news/getById")
    public ResponseEntity<Optional<ConvertNewsInfoDTO>> getByNewsId(@RequestParam Integer newsId) {
        return ResponseEntity.ok(newsInfoService.getById(newsId));
    }

    @GetMapping("/news-image/{newsCategory}/{imageName}")
    public ResponseEntity<InputStreamResource> getImageForNews(@PathVariable("newsCategory") String newsCategory, @PathVariable("imageName") String imageName) {
        return newsInfoService.getImageForNews(newsCategory, imageName);
    }

    @GetMapping("memberCommittee/all")
    public ResponseEntity<?> getAllCommitteeMember(){
        return committeeService.getAllMemberCommittee();
    }


}
