package com.skjenco.hibernateSandbox.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER_ROLE")
public class UserRole {


    @Id
    @GeneratedValue
    private Long id;


    @OneToMany(targetEntity=User.class, mappedBy="userRole",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;


    @Column(name = "ROLE_NAME")
    private String roleName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
