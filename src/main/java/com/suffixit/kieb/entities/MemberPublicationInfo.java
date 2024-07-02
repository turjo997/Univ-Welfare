package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "member_publication_info")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberPublicationInfo {
    @Id
    @SequenceGenerator(name = "member_publication_id_gen", sequenceName = "member_publication_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_publication_id_gen")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "publication_title", nullable = false, length = 200)
    private String publicationTitle;

    @Column(name = "publication_author", nullable = false, length = 200)
    private String publicationAuthor;

    @Column(name = "publication_journal_conference", nullable = false, length = 200)
    private String publicationJournalConference;

    @Column(name = "publication_journal_conference_name", nullable = false, length = 200)
    private String publicationJournalConferenceName;

    @Column(name = "publication_volume", nullable = false, length = 20)
    private String publicationVolume;

    @Column(name = "publication_page", nullable = false, length = 20)
    private String publicationPage;

    @Column(name = "publication_year", nullable = false, length = 10)
    private String publicationYear;

    @Column(name = "publication_month", nullable = false, length = 20)
    private String publicationMonth;

    @Column(name = "publication_publisher", nullable = false, length = 200)
    private String publicationPublisher;

    @Column(name = "publication_url", nullable = false, length = 400)
    private String publicationUrl;

}