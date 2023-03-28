package com.nagp.msa.hotelbooking.model;

public class HotelRoomUpdate {
    public HotelRoomUpdate(int rooms, String hotelName) {
        this.rooms = rooms;
        this.hotelName = hotelName;
    }

    private int rooms;
    private String hotelName;

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
