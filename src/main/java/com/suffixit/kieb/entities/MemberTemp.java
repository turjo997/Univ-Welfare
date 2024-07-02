package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "member_temp")
public class MemberTemp {
    @Id
    @SequenceGenerator(name = "member_temp_id_gen", sequenceName = "member_temp_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_temp_id_gen")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "member_id", nullable = false, length = 15)
    private String memberId;

    @Column(name = "member_temp_id", nullable = false, length = 100)
    private String memberTempId;

    @Column(name = "member_temp_pass", nullable = false, length = 100)
    private String memberTempPass;

    @Column(name = "member_name", nullable = false, length = 100)
    private String memberName;

    @Column(name = "father_name", length = 100)
    private String fatherName;

    @Column(name = "mother_name", length = 100)
    private String motherName;

    @Column(name = "place_of_birth", length = 100)
    private String placeOfBirth;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "age", length = 45)
    private String age;

    @Column(name = "nationality", length = 50)
    private String nationality;

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

    @Column(name = "country_code", length = 10)
    private String countryCode;

    @Column(name = "center_id")
    private Integer centerId;

    @Column(name = "scroll_no")
    private Integer scrollNo;

    @Column(name = "receipt_date")
    private LocalDate receiptDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "picture_name", length = 145)
    private String pictureName;

    @Column(name = "add_user", length = 100)
    private String addUser;

    @Column(name = "add_date")
    private LocalDateTime addDate;

    @Column(name = "mod_user", length = 100)
    private String modUser;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Column(name = "member_type", nullable = false)
    private Integer memberType;

    @Column(name = "member_temp_email_pass", length = 45)
    private String memberTempEmailPass;

    @Column(name = "member_division_id")
    private Integer memberDivisionId;

    @Column(name = "temp_status")
    private Byte tempStatus;

}