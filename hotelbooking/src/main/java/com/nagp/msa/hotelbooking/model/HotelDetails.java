package com.nagp.msa.hotelbooking.model;

public class HotelDetails {
    private String hotelName;
    private String address;
    private int rooms;
    private boolean available;
    private String ManagerName;
    private long ManagerContact;
    private int rating;
    private int price;
    private long hotelContact;

    public HotelDetails(){

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

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getManagerName() {
        return ManagerName;
    }

    public void setManagerName(String managerName) {
        ManagerName = managerName;
    }

    public long getManagerContact() {
        return ManagerContact;
    }

    public void setManagerContact(long managerContact) {
        ManagerContact = managerContact;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getHotelContact() {
        return hotelContact;
    }

    public void setHotelContact(long hotelContact) {
        this.hotelContact = hotelContact;
    }
}
