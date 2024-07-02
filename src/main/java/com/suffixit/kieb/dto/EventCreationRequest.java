package com.suffixit.kieb.dto;

import com.suffixit.kieb.entities.EventCategory;
import com.suffixit.kieb.entities.EventInfo;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventCreationRequest {

    private String eventTitle;
    private String categoryName;
    private String categoryShortName;
    private String eventShortDesc;
    private String eventDate;
    private MultipartFile imageFiles;
}


