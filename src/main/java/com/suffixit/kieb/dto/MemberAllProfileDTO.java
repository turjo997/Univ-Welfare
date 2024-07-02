package com.suffixit.kieb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberAllProfileDTO {

    private Integer memberId;

    private MemberProfileDTO memberProfileDTO;

    private List<MemberAddressDTOtemp> memberAddressDTOtempList;

    private List<MemberEducationInfoResponseDTO> memberEducationInfoDTOList;

    private List<MemberProfessionalInfoDTO> memberProfessionalInfoDTOList;

    private List<MemberProjectInfoDTO> memberProjectInfoDTOList;

    private List<MemberCertificateDTO> memberCertificateDTOList;

    private List<MemberSocialLinkProfileDTO> memberSocialLinkDTOList;

    private List<MemberPublicationInfoProfileDTO> memberPublicationInfoProfileDTOList;

}
