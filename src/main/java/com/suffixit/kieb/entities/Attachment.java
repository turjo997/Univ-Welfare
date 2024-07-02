package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "consumer_id", nullable = false)
    private Integer consumerId;

    @Column(name = "doc_name", nullable = false, length = 450)
    private String docName;

    @Column(name = "attachment")
    private byte[] attachment;

    @Column(name = "file_name", length = 245)
    private String fileName;

    @Column(name = "add_user", nullable = false, length = 100)
    private String addUser;

    @Column(name = "add_dt", nullable = false)
    private LocalDateTime addDt;

    @Column(name = "mod_user", length = 100)
    private String modUser;

    @Column(name = "mod_dt")
    private LocalDateTime modDt;

}