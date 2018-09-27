package com.tsn.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String login;

    @Column(name = "pass")
    private String password;

    @Column(name = "password", nullable = false)
    private String passwordMd5;

    @Column(name = "salt", nullable = false)
    private String saltForPassword;

    @ManyToOne // no cascade type because cascading only (well ALMOST ) makes sense only for Parent – Child associations
    private Role role;

    private String firstName;
    private String middleName;
    private String lastName;
    private String buildingAddress;
    private String apartmentNumber;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Application> applications;

    public User() {
    }

    public int getId() {

        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordMd5() {
        return passwordMd5;
    }

    public void setPasswordMd5(String passwordMd5) {
        this.passwordMd5 = passwordMd5;
    }

    public String getSaltForPassword() {
        return saltForPassword;
    }

    public void setSaltForPassword(String saltForPassword) {
        this.saltForPassword = saltForPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBuildingAddress() {
        return buildingAddress;
    }

    public void setBuildingAddress(String buildingAddress) {
        this.buildingAddress = buildingAddress;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", passwordMd5='" + passwordMd5 + '\'' +
                ", saltForPassword='" + saltForPassword + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", buildingAddress='" + buildingAddress + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", applications=" + applications +
                '}';
    }
}
