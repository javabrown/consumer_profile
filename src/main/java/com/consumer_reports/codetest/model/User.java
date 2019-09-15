package com.consumer_reports.codetest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.function.Consumer;

@JsonPropertyOrder({ "userId", "firstName", "lastName", "email", "address" })
public class User implements Serializable {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer id) {
        this.userId = id;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

//    @JsonIgnore
//    public Builder builder() {
//        return new Builder().with($->{
//            $.userId = getUserId();
//            $.firstName = getFirstName();
//            $.lastName = getLastName();
//            $.email = getEmail();
//        });
//    }

//    @JsonIgnore
//    public Consumer<Builder> builder() {
//        return $ -> {
//            //$.userId = getUserId();
//            $.firstName = getFirstName();
//            $.lastName = getLastName();
//            $.email = getEmail();
//
//            $.street = address.getStreet();
//            $.city = address.getCity();
//            $.state = address.getState();
//            $.zip = address.getZip();
//        };
//    }
}
