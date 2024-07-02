package com.suffixit.kieb.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsCategoryDTO {

    private Integer id;
    private String categoryName;
    private String categoryShortName;
}
