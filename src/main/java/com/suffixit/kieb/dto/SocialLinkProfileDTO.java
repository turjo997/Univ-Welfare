package com.suffixit.kieb.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialLinkProfileDTO {
    private Integer id;
    private String linkName;
}
