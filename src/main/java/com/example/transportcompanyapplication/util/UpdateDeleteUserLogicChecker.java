package com.example.transportcompanyapplication.util;

import com.example.transportcompanyapplication.exceptions.AdminLogicException;
import com.example.transportcompanyapplication.model.User;
import com.example.transportcompanyapplication.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UpdateDeleteUserLogicChecker {
    private final UserRepository repository;

    public UpdateDeleteUserLogicChecker(UserRepository repository) {
        this.repository = repository;
    }

    public void checkDeletionOfLastAdmin(User user){
        Integer numberOfAdmins = repository.numberOfAdmins();
        if(user.isAdmin() && numberOfAdmins == 1){
            throw new AdminLogicException("It is impossible to delete the last admin");
        }
    }

    public void checkDeletionOfLastAdminRole(User oldUser, User updatedUser){
        Integer numberOfAdmins = repository.numberOfAdmins();
        if(oldUser.isAdmin() && !updatedUser.isAdmin() && numberOfAdmins == 1){
            throw new AdminLogicException("It is impossible to delete the role of the last admin");
        }
    }

    public void checkDeletionOfAdminByHimself(User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        if(user.getLogin().equals(userDetails.getUsername()))
        {
            throw new AdminLogicException("It is impossible to delete your own user");
        }
    }

    public void checkDeletionOfRoleAdminByHimself(User oldUser, User newUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        if(oldUser.getLogin().equals(userDetails.getUsername()) && oldUser.isAdmin() && !newUser.isAdmin()){
            throw new AdminLogicException("It is impossible to delete your ADMIN_ROLE");
        }
    }

}
