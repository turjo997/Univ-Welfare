package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumAndSubjectDTO {

       private Integer albumId;
       private String albumName;
       private String albumShortName;
       //private List<SubjectDTO> subjectDTOS;

}
