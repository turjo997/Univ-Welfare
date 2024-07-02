package com.suffixit.kieb.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table//(uniqueConstraints = {@UniqueConstraint(columnNames = {"userName", "email"})})
public class Users implements UserDetails {

    @Id
    @SequenceGenerator(name = "user_id_gen", sequenceName = "user_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
    private int id;
    private String password;
    @Column(unique = true)
    private String userName;
    @Column(unique = true , nullable = false)
    private String email;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialNonExpired = true;
    private boolean enable = true;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // for admin user, it will be null

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;


    public void addRoles(Role role) {
        if(roles == null) roles = new ArrayList<>();
        roles.add(role);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
