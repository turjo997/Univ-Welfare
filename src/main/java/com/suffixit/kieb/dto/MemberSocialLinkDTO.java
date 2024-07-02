package com.suffixit.kieb.dto;

import com.suffixit.kieb.entities.Member;
import com.suffixit.kieb.entities.SocialLink;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSocialLinkDTO {

    private Integer id;

    private  Integer linkTypeId;

    private String link;
}
