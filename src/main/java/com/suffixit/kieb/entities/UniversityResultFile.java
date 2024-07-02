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
@Table(name = "university_result_file")
public class UniversityResultFile {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "file_name", nullable = false, length = 300)
    private String fileName;

    @Column(name = "rec_count", nullable = false)
    private Integer recCount;

    @Column(name = "upload_time")
    private LocalDateTime uploadTime;

    @Column(name = "university_id", nullable = false)
    private Integer universityId;

    @Column(name = "status")
    private Byte status;

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