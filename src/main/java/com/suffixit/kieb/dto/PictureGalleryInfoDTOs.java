package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PictureGalleryInfoDTOs {

    private Integer categoryId;
    private MultipartFile image;
    private String pictureTitle;
}
