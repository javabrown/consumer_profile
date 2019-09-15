package com.consumer_reports.codetest.model;

import com.consumer_reports.codetest.daos.AddressDao;
import com.consumer_reports.codetest.daos.UserDao;

import java.util.function.Consumer;

public class Builder {
    public Integer userId;
    public String firstName;
    public String lastName;
    public String email;

    public Integer addressId;
    public String street;
    public String city;
    public String state;
    public String zip;


    public Builder with(Consumer<Builder> builderFunction) {
        builderFunction.accept(this);
        return this;
    }

    public User makeApiResponseUser() {
        User user = new User();
        user.setUserId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        Address addr = new Address();
        addr.setStreet(street);
        addr.setCity(city);
        addr.setState(state);
        addr.setZip(zip);
        user.setAddress(addr);

        return user;
    }

    public UserDao makeUserDao() {
        UserDao user = new UserDao();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAddressId(addressId);
        return user;
    }

    public AddressDao makeAddressDao() {
        AddressDao addr = new AddressDao();
        addr.setId(addressId);
        addr.setStreet(street);
        addr.setCity(city);
        addr.setState(state);
        addr.setZip(zip);
        return addr;
    }

//    public UserDao makeUpdateDaoUserUser() {
//        UserDao user = new UserDao();
//        user.setId(userId);
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setEmail(email);
//
////        AddressDao addr = new AddressDao();
////        addr.setStreet(street);
////        addr.setCity(city);
////        addr.setState(state);
////        addr.setZip(zip);
////        user.setAddress(addr);
//
//        return user;
//    }
}