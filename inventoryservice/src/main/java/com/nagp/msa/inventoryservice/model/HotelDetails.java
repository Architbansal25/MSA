package com.nagp.msa.inventoryservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelDetails {
    private int hotelID;
    private String hotelName;
    private String address;
    private HotelRoomDetails rooms;
    private HotelManagerDetails hotelManagerDetails;
    private int rating;
    private int pricePerNight;
    private long hotelContact;

    @Override
    public String toString() {
        return "HotelDetails{" +
                "hotelID=" + hotelID +
                ", hotelName='" + hotelName + '\'' +
                ", address='" + address + '\'' +
                ", rooms=" + rooms +
                ", hotelManagerDetails=" + hotelManagerDetails +
                ", rating=" + rating +
                ", pricePerNight=" + pricePerNight +
                ", hotelContact=" + hotelContact +
                '}';
    }

    @JsonCreator
    public HotelDetails(@JsonProperty("hotelID") int hotelID,
                        @JsonProperty("hotelName")String hotelName,
                        @JsonProperty("address")String address,
                        @JsonProperty("hotelroomDetails")HotelRoomDetails rooms,
                        @JsonProperty("hotelManagerDetails")HotelManagerDetails hotelManagerDetails,
                        @JsonProperty("rating")int rating,
                        @JsonProperty("price")int pricePerNight,
                        @JsonProperty("contact")long hotelContact) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.address = address;
        this.rooms = rooms;
        this.hotelManagerDetails = hotelManagerDetails;
        this.rating = rating;
        this.pricePerNight = pricePerNight;
        this.hotelContact = hotelContact;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HotelRoomDetails getRooms() {
        return rooms;
    }

    public void setRooms(HotelRoomDetails rooms) {
        this.rooms = rooms;
    }

    public HotelManagerDetails getHotelManagerDetails() {
        return hotelManagerDetails;
    }

    public void setHotelManagerDetails(HotelManagerDetails hotelManagerDetails) {
        this.hotelManagerDetails = hotelManagerDetails;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public long getHotelContact() {
        return hotelContact;
    }

    public void setHotelContact(long hotelContact) {
        this.hotelContact = hotelContact;
    }
}
