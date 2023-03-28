package com.nagp.msa.inventoryservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class HotelRoomDetails {
    private int rooms;
    private String HotelName;
    private boolean available;

    @JsonCreator
    public HotelRoomDetails(@JsonProperty("rooms")int rooms,
                            @JsonProperty("hotelName") String hotelName,
                            @JsonProperty("available")boolean available) {
        this.rooms = rooms;
        HotelName = hotelName;
        this.available = available;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
