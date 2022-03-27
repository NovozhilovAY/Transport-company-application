package com.example.transportcompanyapplication.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "users", indexes = {
        @Index(name = "users_login_key", columnList = "login", unique = true)
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "field 'login' should not be empty")
    @Column(name = "login", nullable = false)
    private String login;

    @NotBlank(message = "field 'password' should not be empty")
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))

    private List<Role> roles;

    public User() {
    }

    public User(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        ArrayList<Role> roles = new ArrayList<>();
        for (Role role:user.getRoles()){
            roles.add(new Role(role));
        }
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAdmin(){
        for (Role role:roles){
            if("ROLE_ADMIN".equals(role.getName())){
                return true;
            }
        }
        return false;
    }
}