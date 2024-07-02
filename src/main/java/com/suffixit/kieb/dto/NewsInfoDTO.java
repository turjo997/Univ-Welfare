package com.suffixit.kieb.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsInfoDTO {
    private Integer id;
    private String categoryName;
    private String categoryShortName;
    private String newsTitle;
    private MultipartFile image1;
    private String newsShortDesc;
    private String newsDate;
    private Byte published;
    private String addUser;
    private String addDate;
    private Byte isSticky;

}
