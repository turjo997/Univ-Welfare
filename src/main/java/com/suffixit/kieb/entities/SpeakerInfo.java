package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "speaker_info")
public class SpeakerInfo {
    @Id
    @Column(name = "id_speaker", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_event", nullable = false)
    private EventInfo idEvent;

    
    @Column(name = "speaker_name", nullable = false)
    private String speakerName;

    @Column(name = "speaker_designation", length = 100)
    private String speakerDesignation;

    
    @Column(name = "speaker_short_desc", nullable = false)
    private String speakerShortDesc;

    
    @Column(name = "speaker_desc")
    private String speakerDesc;

    @Column(name = "speaker_image", length = 100)
    private String speakerImage;

    @Column(name = "attachment", length = 100)
    private String attachment;

    @Column(name = "showing_order", length = 45)
    private String showingOrder;

    @Column(name = "add_user", length = 45)
    private String addUser;

    @Column(name = "add_date", length = 45)
    private String addDate;

    @Column(name = "add_term", length = 45)
    private String addTerm;

    @Column(name = "add_ip", length = 45)
    private String addIp;

    @Column(name = "mod_user", length = 45)
    private String modUser;

    @Column(name = "mod_date", length = 45)
    private String modDate;

    @Column(name = "mod_term", length = 45)
    private String modTerm;

    @Column(name = "mod_ip", length = 45)
    private String modIp;

}