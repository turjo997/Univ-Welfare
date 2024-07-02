package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "committee")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Committee {

    @Id
    @SequenceGenerator(name = "committee_id_gen", sequenceName = "committee_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "committee_id_gen")
    @Column(name = "id_committee", nullable = false)
    private Integer id;

    @Column(name = "committee_type", length = 45)
    private String committeeType;

    @Column(name = "committee_name", nullable = false, length = 300)
    private String committeeName;

    @Column(name = "committee_short_name", length = 300)
    private String committeeShortName;

    @Column(name = "committee_duration", length = 45)
    private String committeeDuration;


    @Column(name = "committee_desc")
    private String committeeDesc;

    @Column(name = "status")
    private Byte status;

    @Column(name = "showing_order")
    private Integer showingOrder;

    @Column(name = "feature_image", length = 45)
    private String featureImage;

    @Column(name = "published")
    private Byte published;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "com_cat_id")
    private CommitteeCategory comCat;

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