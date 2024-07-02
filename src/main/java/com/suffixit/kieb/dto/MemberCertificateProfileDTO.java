package com.suffixit.kieb.dto;

import com.suffixit.kieb.entities.Member;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberCertificateProfileDTO {

    private Integer id;

    private Integer member;

    private String certificateShortName;

    private String certificateLongName;

    private String certificateFileName;
}
