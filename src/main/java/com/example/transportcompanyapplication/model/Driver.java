package com.example.transportcompanyapplication.model;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@DynamicUpdate
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    @NotEmpty(message = "last name should not be empty!")
    private String lastName;

    @Column(name = "first_name")
    @NotEmpty(message = "first name should not be empty!")
    private String firstName;

    @Column(name = "middle_name")
    @NotEmpty(message = "middle name should not be empty!")
    private String middleName;

    @Column(name = "driving_license")
    @NotEmpty(message = "field 'drivingLicense' should not be empty!")
    private String drivingLicense;

    public Driver() {
    }

    public Driver(Long id, String lastName, String firstName, String middleName, String drivingLicense) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.drivingLicense = drivingLicense;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }
}
