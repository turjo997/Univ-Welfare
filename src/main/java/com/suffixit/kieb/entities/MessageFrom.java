package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "message_from")
public class MessageFrom {
    @Id
    @Column(name = "id_message", nullable = false)
    private Integer id;

    @Column(name = "message_name", length = 300)
    private String messageName;

    
    @Column(name = "message_title")
    private String messageTitle;

    
    @Column(name = "message_short_desc")
    private String messageShortDesc;

    
    @Column(name = "message_desc")
    private String messageDesc;

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

    @Column(name = "ADD_USER", length = 45)
    private String addUser;

    @Column(name = "ADD_DATE", length = 45)
    private String addDate;

    @Column(name = "ADD_TERM", length = 45)
    private String addTerm;

    @Column(name = "ADD_IP", length = 45)
    private String addIp;

    @Column(name = "MOD_USER", length = 45)
    private String modUser;

    @Column(name = "MOD_DATE", length = 45)
    private String modDate;

    @Column(name = "MOD_TERM", length = 45)
    private String modTerm;

    @Column(name = "MOD_IP", length = 45)
    private String modIp;

}