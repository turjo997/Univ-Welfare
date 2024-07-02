package com.suffixit.kieb.dto;

import com.suffixit.kieb.entities.PictureGalleryInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PictureGalleryDTO {

    private Integer pictureCategoryId;
    private String pictureCategory;
    private String pictureCategoryShortName;
    private List<PictureGalleryInfoDTO> pictureGalleryInfoDTOList;

}
