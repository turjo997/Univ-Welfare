package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVideoCategoryDTO {
    private Integer id;
    private String categoryName;
    private String categoryShortName;
    private MultipartFile image1;
    private String modUser;
    private String modDate;
}
