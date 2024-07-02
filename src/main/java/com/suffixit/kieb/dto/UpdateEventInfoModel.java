package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventInfoModel {

    private Integer id;
    private Integer catId;
    private String eventTitle;
    private String categoryName;
    private String categoryShortName;
    private String eventShortDesc;
    private String eventDate;
    private MultipartFile image1;
    private String modUser;
    private String modDate;

}
