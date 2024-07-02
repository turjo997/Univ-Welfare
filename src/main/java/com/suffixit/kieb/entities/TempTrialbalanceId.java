package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class TempTrialbalanceId implements Serializable {
    private static final long serialVersionUID = 7435664048720623867L;
    @Column(name = "gl_code", nullable = false)
    private Integer glCode;

    @Column(name = "user_id", nullable = false, length = 100)
    private String userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TempTrialbalanceId entity = (TempTrialbalanceId) o;
        return Objects.equals(this.glCode, entity.glCode) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(glCode, userId);
    }

}