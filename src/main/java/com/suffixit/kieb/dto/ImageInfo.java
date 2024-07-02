package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.InputStreamResource;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageInfo {

    private Integer id;
    private String categoryName;
    private String fileName;
}
