package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventInfoDTO {

    private Integer id;
    private Integer categoryId;
    private String categoryName;
    private String categoryShortName;
    private String eventTitle;
    private String eventShortDesc;
    private String description;
    private String addDate;
    private String addUser;
    private String eventDate;
    private String modDate;
    private String modUser;
    private String image1;
}
