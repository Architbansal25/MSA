package com.nagp.msa.inventoryservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelManagerDetails {
    private String hotelManagerName;
    private long hotelManagerContact;
    private String hotelName;
    @JsonCreator
    public HotelManagerDetails(@JsonProperty("hotelManagerName")String hotelManagerName,
                               @JsonProperty("hotelManagerContact")long hotelManagerContact,
                               @JsonProperty("hotelName")String hotelName) {
        this.hotelManagerName = hotelManagerName;
        this.hotelManagerContact = hotelManagerContact;
        this.hotelName = hotelName;
    }

    public String getHotelManagerName() {
        return hotelManagerName;
    }

    public void setHotelManagerName(String hotelManagerName) {
        this.hotelManagerName = hotelManagerName;
    }

    public long getHotelManagerContact() {
        return hotelManagerContact;
    }

    public void setHotelManagerContact(long hotelManagerContact) {
        this.hotelManagerContact = hotelManagerContact;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
