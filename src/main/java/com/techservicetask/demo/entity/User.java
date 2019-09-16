package com.techservicetask.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfiles;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FetchProfile(name = User.USER_WITH_ROLE, fetchOverrides = @FetchProfile.FetchOverride(entity = User.class,
        association = "role", mode = FetchMode.JOIN))
public class User extends AbstractEntity  {

    public static final String USER_WITH_ROLE = "user-with-role";

    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 20)
    private String username;

    @Column(name = "PASSWORD", length = 20)
    @NotNull
    @Size(min = 4, max = 256)
    private String password;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;

    @Column(name = "LAST_PASSWORD_RESET_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users")
    @JsonBackReference
    private Role role;

}