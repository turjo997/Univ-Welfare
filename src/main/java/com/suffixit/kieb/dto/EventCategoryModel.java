package com.suffixit.kieb.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventCategoryModel {

    private Integer id;
    private String categoryName;
    private String categoryShortName;
}
