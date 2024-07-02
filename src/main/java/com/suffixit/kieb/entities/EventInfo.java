package com.suffixit.kieb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "event_info")
public class EventInfo implements Serializable {
    @Id
    @SequenceGenerator(name = "event_id_gen", sequenceName = "event_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_id_gen")

    @Column(name = "id_event", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_category", nullable = false)
    private EventCategory eventCategory;

    @Column(name = "event_title", nullable = false)
    private String eventTitle;

    
    @Column(name = "event_short_desc", nullable = false)
    private String eventShortDesc;

    
    @Column(name = "event_desc")
    private String eventDesc;

    @Column(name = "event_date", nullable = false, length = 50)
    private String eventDate;

    @Column(name = "event_date_time")
    private LocalDateTime eventDateTime;

    @Column(name = "attachment", length = 100)
    private String attachment;

    @Column(name = "event_duration", length = 45)
    private String eventDuration;

    @Column(name = "event_venue", length = 100)
    private String eventVenue;

    @Column(name = "showing_order", length = 45)
    private String showingOrder;

    @Column(name = "feature_image", length = 45)
    private String featureImage;

    @Column(name = "image1", length = 45)
    private String image1;

    @Column(name = "image2", length = 45)
    private String image2;

    @Column(name = "image3", length = 45)
    private String image3;

    @Column(name = "image4", length = 45)
    private String image4;

    @Column(name = "published")
    private Byte published;

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