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
@Table(name = "upload_log")
public class UploadLog {
    @Id
    @Column(name = "FILE_NAME", nullable = false, length = 100)
    private String fileName;

    @Column(name = "REC_COUNT")
    private Integer recCount;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "order_no", length = 100)
    private String orderNo;

    @Column(name = "STATUS", length = 1)
    private String status;

    @Column(name = "USER_ID", length = 100)
    private String userId;

    @Column(name = "UPLOAD_TIME")
    private LocalDateTime uploadTime;

    @Column(name = "UPLOAD_TERM", length = 100)
    private String uploadTerm;

}