package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventVideoDto {
    private Integer id;
    private Integer eventId;
    private Integer videoGalleryId;
}
