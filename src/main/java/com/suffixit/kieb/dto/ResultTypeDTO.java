package com.suffixit.kieb.dto;

import jakarta.mail.search.SearchTerm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultTypeDTO {

    private Integer id;
    private String resultType;
}
