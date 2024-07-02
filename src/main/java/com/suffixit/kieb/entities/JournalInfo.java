package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "journal_info")
public class JournalInfo {
    @Id
    @Column(name = "id_journal", nullable = false)
    private Integer id;

    @Column(name = "parent_id", nullable = false)
    private Integer parentId;

    @Column(name = "journalOfficeId", length = 100)
    private String journalOfficeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journal_division")
    private MemberDivision journalDivision;

    @Column(name = "journal_title", nullable = false, length = 500)
    private String journalTitle;

    @Column(name = "journal_author", length = 500)
    private String journalAuthor;

    
    @Column(name = "journal_desc")
    private String journalDesc;

    @Column(name = "cover_page_picture", length = 100)
    private String coverPagePicture;

    @Column(name = "cover_page_thumb", length = 100)
    private String coverPageThumb;

    @Column(name = "journal_link", length = 300)
    private String journalLink;

    @Column(name = "journal_date")
    private LocalDateTime journalDate;

    @Column(name = "custom_orderby")
    private Integer customOrderby;

    @Column(name = "published", nullable = false)
    private Byte published;

    @Column(name = "add_date", length = 45)
    private String addDate;

    @Column(name = "add_ip", length = 45)
    private String addIp;

    @Column(name = "add_term", length = 45)
    private String addTerm;

    @Column(name = "add_user", length = 45)
    private String addUser;

    @Column(name = "mod_date", length = 45)
    private String modDate;

    @Column(name = "mod_ip", length = 45)
    private String modIp;

    @Column(name = "mod_term", length = 45)
    private String modTerm;

    @Column(name = "mod_user", length = 45)
    private String modUser;

}