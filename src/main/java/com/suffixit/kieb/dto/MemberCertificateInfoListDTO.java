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
public class MemberCertificateInfoListDTO {

    private Integer memberId;

    private List<MemberCertificateDTO> memberCertificateList;
}
