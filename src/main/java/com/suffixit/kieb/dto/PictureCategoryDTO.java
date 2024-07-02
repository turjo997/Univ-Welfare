package com.suffixit.kieb.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PictureCategoryDTO {

    private Integer id;

    private String categoryName;
    private String categoryShortName;

    private List<PictureGalleryDTO> pictureGalleryDTOList;


    private Integer albumId;

    private String subjectCode;
    private Integer divisionId;
    private Integer centerId;
    private Integer eventId;
    private Integer newFieldId;
    private Integer slugId;

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
