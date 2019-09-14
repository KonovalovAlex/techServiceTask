package com.techservicetask.demo.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role extends AbstractEntity {

    @Column(name="ROLE")
    private String role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role", orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @JsonManagedReference
    private Set<User> users;

}
