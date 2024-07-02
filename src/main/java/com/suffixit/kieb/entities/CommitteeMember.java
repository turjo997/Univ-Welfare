package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "committee_member")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommitteeMember implements Serializable {

    @Id
    @SequenceGenerator(name = "committee_member_id_gen", sequenceName = "committee_member_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "committee_member_id_gen")
    @Column(name = "id_committee_member", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_committtee")
    private Committee committee;

    @ManyToOne
    private Member member;

    @ManyToOne
    private CommitteeDesignation committeeDesignation;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "committee_department_id", nullable = false)
//    private CommitteeDepartment committeeDepartment;

    @Column(name = "committee_showing_order")
    private Integer committeeShowingOrder;

    
    @Column(name = "member_deatils")
    private String memberDeatils;

    @Column(name = "member_short_deatils", length = 300)
    private String memberShortDeatils;

    @Column(name = "leader", nullable = false)
    private Byte leader;

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