package com.example.transportcompanyapplication.dto;

import com.example.transportcompanyapplication.model.Role;
import com.example.transportcompanyapplication.model.User;
import java.util.List;

public class UserWithoutPass {
    private Integer id;
    private String login;
    private List<Role> roles;

    public UserWithoutPass(Integer id, String login, List<Role> roles) {
        this.id = id;
        this.login = login;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public static UserWithoutPass fromUser(User user){
        return new UserWithoutPass(user.getId(), user.getLogin(), user.getRoles());
    }
}
