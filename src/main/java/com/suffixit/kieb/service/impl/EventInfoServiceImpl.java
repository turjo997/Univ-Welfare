package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.EventCreationRequest;
import com.suffixit.kieb.dto.EventInfoDTO;
import com.suffixit.kieb.dto.UpdateEventInfoModel;
import com.suffixit.kieb.entities.EventCategory;
import com.suffixit.kieb.entities.EventInfo;
import com.suffixit.kieb.repository.EventCategoryRepository;
import com.suffixit.kieb.repository.EventInfoRepository;
import com.suffixit.kieb.service.EventCategoryService;
import com.suffixit.kieb.service.EventInfoService;
import com.suffixit.kieb.utils.FileUploadUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service

public class EventInfoServiceImpl implements EventInfoService {
    private final String uploadDir;
    private final EventCategoryService eventCategoryService;
    private final EventCategoryRepository eventCategoryRepository;
    private final EventInfoRepository infoRepository;


    public EventInfoServiceImpl(EventCategoryService categoryService, EventInfoRepository infoRepository, @Value("${fileStore.directory}") String uploadDir, EventCategoryRepository eventCategoryRepository) {
        this.eventCategoryService = categoryService;
        this.infoRepository = infoRepository;
        this.uploadDir = uploadDir;
        this.eventCategoryRepository = eventCategoryRepository;


    }

    @Override
    @Transactional
    public String createEventInfo(EventCreationRequest model) throws IOException {
        if (infoRepository.findByEventTitle(model.getEventTitle()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "event title already exist");
        }
        if (!FileUploadUtil.isImageFile(model.getImageFiles())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "only image files are allow");
        }
        String categoryName = "/event/" + model.getCategoryName();
        Path categoryDir = createCategoryDirectory(categoryName);
        String imageName = model.getImageFiles().getOriginalFilename();
        saveImage(categoryDir, imageName, model.getImageFiles());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EventCategory category = new EventCategory();
        category.setCategoryName(model.getCategoryName());
        category.setCategoryShortName(model.getCategoryShortName());
        eventCategoryRepository.save(category);

        EventInfo event = new EventInfo();
        event.setEventTitle(model.getEventTitle());
        event.setEventCategory(category);
        event.setEventShortDesc(model.getEventShortDesc());
        event.setEventCategory(category);
        event.setAddUser(authentication.getName());
        event.setAddDate(LocalDateTime.now().toString());
        event.setEventDate(model.getEventDate());
        event.setImage1(imageName);
        infoRepository.save(event);


        return "event create successfully";
    }

    @Override
    public Page<EventInfoDTO> getAll(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<EventInfo> eventsPage;


        eventsPage = infoRepository.findAll(pageable);

        if (eventsPage.isEmpty()) {
            return Page.empty();
        }

        List<EventInfoDTO> resultList = new ArrayList<>();


        eventsPage.getContent().forEach(eventInfo -> {

            EventInfoDTO eventInfoDTO = EventInfoDTO.builder().id(eventInfo.getId())
                    .categoryId(eventInfo.getEventCategory().getId())
                    .categoryName(eventInfo.getEventCategory().getCategoryName()).categoryShortName(eventInfo.getEventCategory().getCategoryShortName()).eventTitle(eventInfo.getEventTitle()).eventDate(eventInfo.getEventDate()).eventShortDesc(eventInfo.getEventShortDesc()).addDate(eventInfo.getAddDate()).addUser(eventInfo.getAddUser()).modDate(eventInfo.getModDate()).modUser(eventInfo.getModUser()).image1(eventInfo.getImage1()).build();

            resultList.add(eventInfoDTO);
        });

        return new PageImpl<>(resultList, eventsPage.getPageable(), eventsPage.getTotalElements());


    }

    @Override
    public Optional<EventInfoDTO> getById(Integer eventId) {
       Optional <EventInfo> eventInfo= infoRepository.findById(eventId);
       return eventInfo.map(this::newModel);
    }

    @Override
    public List<EventInfoDTO> getByCategoryId(Integer catId) {
        EventCategory eventCategory = eventCategoryService.getByCatId(catId);
        List<EventInfo> eventInfoList = infoRepository.findAllByEventCategoryOrderByEventTitle(eventCategory);
        return eventInfoList.stream().map(this::newModel).toList();
    }


    @Override
    public String updateEventInfo(UpdateEventInfoModel updateModel) throws IOException {
        EventInfo eventInfo = infoRepository.findById(updateModel.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "event id not found"));
        EventCategory eventCategory1 = eventCategoryRepository.findById(updateModel.getCatId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "event category id not found"));

        //        if (!FileUploadUtil.isImageFile(updateModel.getImage1())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "only image files are allow");
//        }
        String categoryName = "/event/" + updateModel.getCategoryName();
        Path categoryDir = createCategoryDirectory(categoryName);
        String imageName = null;
        if(updateModel.getImage1()!=null){
            imageName=updateModel.getImage1().getOriginalFilename();
            saveImage(categoryDir, imageName, updateModel.getImage1());
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        eventCategory1.setCategoryShortName(updateModel.getCategoryName());
        eventCategory1.setCategoryShortName(updateModel.getCategoryShortName());
        eventCategoryRepository.save(eventCategory1);
        eventInfo.setEventTitle(updateModel.getEventTitle());
        eventInfo.setEventCategory(eventCategory1);
        eventInfo.setModUser(authentication.getName());
        eventInfo.setEventShortDesc(updateModel.getEventShortDesc());
        eventInfo.setEventDate(updateModel.getEventDate());
        eventInfo.setModDate(LocalDateTime.now().toString());
        if(imageName!=null){
            eventInfo.setImage1(imageName);
        }

        infoRepository.save(eventInfo);
        return "successfully updated eventInfo";
    }

    @Override
    public ResponseEntity<InputStreamResource> getImageForEvent(String eventCategory, String imageName) {
        try {
            String imageDirectory = uploadDir + "/event/" + eventCategory;

            if (imageName != null) {
                Path imagePath = Paths.get(imageDirectory, imageName);

                if (Files.exists(imagePath)) {
                    InputStreamResource resource = new InputStreamResource(new FileInputStream(imagePath.toFile()));

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.IMAGE_JPEG);

                    return ResponseEntity.ok().headers(headers).body(resource);
                } else {

                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
                }
            } else {

                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image name is null");
            }

        } catch (IOException e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Something bad occurred");
        }
    }




    private Path createCategoryDirectory(String categoryName) throws IOException {
        Path categoryDir = Paths.get(uploadDir, categoryName);
        return Files.createDirectories(categoryDir);
    }

    private void saveImage(Path categoryDir, String fileName, MultipartFile image1) throws IOException {
        if (image1 != null && !image1.isEmpty()) {
            FileUploadUtil.saveFile(categoryDir.toString(), fileName, image1);
        }
    }


    private EventInfoDTO newModel(EventInfo eventInfo) {
        EventInfoDTO model = new EventInfoDTO();
        model.setId(eventInfo.getId());
        model.setCategoryId(eventInfo.getEventCategory().getId());
        model.setCategoryName(eventInfo.getEventCategory().getCategoryName());
        model.setCategoryShortName(eventInfo.getEventCategory().getCategoryShortName());
        model.setEventTitle(eventInfo.getEventTitle());
        model.setEventShortDesc(eventInfo.getEventShortDesc());
        model.setEventDate(eventInfo.getEventDate());
        model.setAddDate(eventInfo.getAddDate());
        model.setAddUser(eventInfo.getAddUser());
        model.setModUser(eventInfo.getModUser());
        model.setModDate(eventInfo.getModDate());
        model.setImage1(eventInfo.getImage1());
        return model;

    }
}

