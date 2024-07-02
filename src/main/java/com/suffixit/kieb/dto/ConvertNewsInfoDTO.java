package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ConvertNewsInfoDTO {
    private Integer id;
    private Integer categoryId;
    private String newsCategoryName;
    private String categoryShortName;
    private String newsTitle;
    private String newsShortDesc;
    private String newsDate;
    private String addDate;
    private String addUser;
    private String modDate;
    private String modUser;
    private Byte isSticky;
    private String image1;
}
