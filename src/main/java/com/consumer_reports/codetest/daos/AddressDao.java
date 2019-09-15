package com.consumer_reports.codetest.daos;

import com.consumer_reports.codetest.core.Utils;
import com.consumer_reports.codetest.model.Builder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.function.Consumer;


@Table(name = "addresses")
@Entity
public class AddressDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    public String street;
    public String city;
    public String state;
    public String zip;


    public Integer getId() {
        return id;
    }

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

    public Consumer<Builder> builder() {
        return $->{
            $.addressId = id;
            $.street = street;
            $.city = city;
            $.state = state;
            $.zip = zip;
        };
    }

    public boolean isValid() {
        return !Utils.isEmpty(street) || !Utils.isEmpty(city) || !Utils.isEmpty(state) || !Utils.isEmpty(zip);
    }

}
