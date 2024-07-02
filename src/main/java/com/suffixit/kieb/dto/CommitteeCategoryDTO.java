package com.suffixit.kieb.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommitteeCategoryDTO {

    private Integer id;

    private String categoryName;

    private String categoryShortName;

    private String showingOrder;

    private MultipartFile featureImage;

    private MultipartFile image1;

    private MultipartFile image2;

    private MultipartFile image3;

    private MultipartFile image4;

    private Byte published;

    private String addUser;

    private String addDate;

    private String addTerm;

    private String addIp;

    private String modUser;

    private String modDate;

    private String modTerm;

    private String modIp;

}
