package com.suffixit.kieb.dto;

import jdk.jfr.Label;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventNewsDto {

    private Integer id;
    private Integer eventId;
    private Integer newsId;

}
