package com.example.springsecuritydemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role extends AbstractEntity {

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roleList", fetch = FetchType.EAGER)
    private List<User> user;

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}