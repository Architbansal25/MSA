package com.nagp.msa.searchservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class SearchHotelParams {
    private String city;
    private int rooms;
    private String checkIN;
    private String checkOut;

    @JsonCreator
    public SearchHotelParams(@JsonProperty("city") String city, @JsonProperty("rooms")int rooms,
                             @JsonProperty("checkIn") String checkIN,
                             @JsonProperty("checkOut") String checkOut) {
        this.city = city;
        this.rooms = rooms;
        this.checkIN = checkIN;
        this.checkOut = checkOut;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getCheckIN() {
        return checkIN;
    }

    public void setCheckIN(String checkIN) {
        this.checkIN = checkIN;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
}
