package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "success_story")
public class SuccessStory {
    @Id
    @Column(name = "id_story", nullable = false)
    private Integer id;

    @Column(name = "member_id")
    private Integer memberId;

    
    @Column(name = "story_title", nullable = false)
    private String storyTitle;

    
    @Column(name = "story_short_desc", nullable = false)
    private String storyShortDesc;

    
    @Column(name = "story_desc")
    private String storyDesc;

    @Column(name = "showing_order")
    private Integer showingOrder;

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