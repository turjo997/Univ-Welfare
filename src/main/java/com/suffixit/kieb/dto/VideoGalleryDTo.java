package com.suffixit.kieb.dto;

import com.suffixit.kieb.entities.VideoCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoGalleryDTo {

    private Integer id;
    private Integer videoCategoryId;
    private String videoTitle;
    private String videoCaption;
    private String videoOrginalLink;
    private String videoEmdedLink;
    private MultipartFile featureImage;

    private Byte published;
    private String addUser;
    private String addDate;
    private String modUser;
    private String modDate;
}
