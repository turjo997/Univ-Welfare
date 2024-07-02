package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNewsInfoDTO {
    private Integer id;
    private Integer categoryId;
    private String categoryName;
    private String CategoryShortName;
    private String newsTitle;
    private MultipartFile image1;
    private String newsShortDesc;
    private String newsDate;
    private Byte published;
    private String modUser;
    private String modDate;
    private Byte isSticky;
}
