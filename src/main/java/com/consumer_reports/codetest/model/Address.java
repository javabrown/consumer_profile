package com.consumer_reports.codetest.model;

import com.consumer_reports.codetest.core.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.function.Consumer;

public class Address implements Serializable {
    private Integer id;
    public String street;
    public String city;
    public String state;
    public String zip;

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @JsonIgnore
    public Consumer<Builder> builder() {
        return $->{
            $.addressId = id;
            $.street = street;
            $.city = city;
            $.state = state;
            $.zip = zip;
        };
    }

    @JsonIgnore
    public boolean isValid() {
        return !Utils.isEmpty(street) || !Utils.isEmpty(city) || !Utils.isEmpty(state) || !Utils.isEmpty(zip);
    }
}
