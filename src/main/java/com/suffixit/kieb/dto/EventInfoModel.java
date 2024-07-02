package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventInfoModel {
    private Integer id;
    private String categoryName;
    private String categoryShortName;
    private String eventTitle;
    private String eventShortDesc;
    private String eventDate;
    private MultipartFile image1;
    private String addUser;
    private String addDate;
    private String modUser;
    private String modDate;
    private Integer catId;
}
