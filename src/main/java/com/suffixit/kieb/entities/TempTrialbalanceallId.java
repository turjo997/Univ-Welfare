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
public class TempTrialbalanceallId implements Serializable {
    private static final long serialVersionUID = 7617685817192364327L;
    @Column(name = "gl_narration", nullable = false, length = 200)
    private String glNarration;

    @Column(name = "user_id", nullable = false, length = 100)
    private String userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TempTrialbalanceallId entity = (TempTrialbalanceallId) o;
        return Objects.equals(this.glNarration, entity.glNarration) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(glNarration, userId);
    }

}