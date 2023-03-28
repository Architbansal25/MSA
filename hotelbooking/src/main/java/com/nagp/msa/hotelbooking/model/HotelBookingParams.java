package com.nagp.msa.hotelbooking.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelBookingParams {

    private int hotelId;
    private int rooms;
    private String checkInDate;
    private String getCheckOutDate;

    @JsonCreator
    public HotelBookingParams(@JsonProperty("hotelId") int hotelId,
                              @JsonProperty("rooms")int rooms,
                              @JsonProperty("checkInDate")String checkInDate,
                              @JsonProperty("checOutDate")String getCheckOutDate) {
        this.hotelId = hotelId;
        this.rooms = rooms;
        this.checkInDate = checkInDate;
        this.getCheckOutDate = getCheckOutDate;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getGetCheckOutDate() {
        return getCheckOutDate;
    }

    public void setGetCheckOutDate(String getCheckOutDate) {
        this.getCheckOutDate = getCheckOutDate;
    }
}
