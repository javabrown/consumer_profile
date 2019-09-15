package com.consumer_reports.codetest.daos;

import com.consumer_reports.codetest.model.Builder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.function.Consumer;

@Table(name = "users")
@Entity
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = false)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer addressId;

    public UserDao() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @JsonIgnore
    public Consumer<Builder> builder() {
        return $ -> {
            $.userId = id;
            $.firstName = firstName;
            $.lastName = lastName;
            $.email = email;
        };
    }

    @Override
    public String toString() {
        return "UserDao{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", addressId='" + addressId + '\'' +
                '}';
    }
}