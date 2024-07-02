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
public class MemberPublicationInfoProfileDTO {

    private Integer id;

    private String publicationTitle;

    private String publicationAuthor;

    private String publicationJournalConference;

    private String publicationJournalConferenceName;

    private String publicationVolume;

    private String publicationPage;

    private String publicationYear;

    private String publicationMonth;

    private String publicationPublisher;

    private String publicationUrl;
}
