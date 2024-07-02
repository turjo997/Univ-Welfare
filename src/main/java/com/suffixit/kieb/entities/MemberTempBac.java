package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "member_temp_bac")
public class MemberTempBac {
    @Column(name = "id", nullable = false)
    @Id
    private Integer id;

    @Column(name = "member_id", nullable = false, length = 15)
    private String memberId;

    @Column(name = "member_temp_id", length = 100)
    private String memberTempId;

    @Column(name = "member_temp_pass", length = 100)
    private String memberTempPass;

    @Column(name = "member_name", nullable = false, length = 100)
    private String memberName;

    @Column(name = "place_of_birth", length = 100)
    private String placeOfBirth;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "gender", nullable = false, length = 1)
    private String gender;

    @Column(name = "blood_group", length = 20)
    private String bloodGroup;

    @Column(name = "apply_for", length = 400)
    private String applyFor;

    @Column(name = "old_member_id", length = 15)
    private String oldMemberId;

    @Column(name = "phone1", length = 50)
    private String phone1;

    @Column(name = "phone2", length = 50)
    private String phone2;

    @Column(name = "mobile", nullable = false, length = 50)
    private String mobile;

    @Column(name = "email_id", nullable = false, length = 200)
    private String emailId;

    @Column(name = "country_code", nullable = false, length = 10)
    private String countryCode;

    @Column(name = "center_id", nullable = false)
    private Integer centerId;

    @Column(name = "scroll_no", nullable = false)
    private Integer scrollNo;

    @Column(name = "receipt_date")
    private LocalDate receiptDate;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "member_subdivision_id", nullable = false)
    private Integer memberSubdivisionId;

    @Column(name = "add_user", nullable = false, length = 100)
    private String addUser;

    @Column(name = "add_date", nullable = false)
    private LocalDateTime addDate;

    @Column(name = "mod_user", length = 100)
    private String modUser;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

}