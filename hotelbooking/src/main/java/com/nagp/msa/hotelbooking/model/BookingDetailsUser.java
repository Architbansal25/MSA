package com.nagp.msa.hotelbooking.model;

public class BookingDetailsUser {
    private int bookingId;
    private String hotelName;
    private int rooms;
    private int deductedMoney;
    private String checkInDate;
    private String checkOutDate;
     private String HotelManagerName;
     private  long HotelManagerContact;

    @Override
    public String toString() {
        return "BookingDetailsUser{" +
                "bookingId=" + bookingId +
                ", hotelName='" + hotelName + '\'' +
                ", rooms=" + rooms +
                ", deductedMoney=" + deductedMoney +
                ", checkInDate='" + checkInDate + '\'' +
                ", checkOutDate='" + checkOutDate + '\'' +
                ", HotelManagerName='" + HotelManagerName + '\'' +
                ", HotelManagerContact=" + HotelManagerContact +
                '}';
    }

    public BookingDetailsUser(int bookingId, String hotelName, int rooms, int deductedMoney, String checkInDate, String checkOutDate, String hotelManagerName, long hotelManagerContact) {
        this.bookingId = bookingId;
        this.hotelName = hotelName;
        this.rooms = rooms;
        this.deductedMoney = deductedMoney;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        HotelManagerName = hotelManagerName;
        HotelManagerContact = hotelManagerContact;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getDeductedMoney() {
        return deductedMoney;
    }

    public void setDeductedMoney(int deductedMoney) {
        this.deductedMoney = deductedMoney;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getHotelManagerName() {
        return HotelManagerName;
    }

    public void setHotelManagerName(String hotelManagerName) {
        HotelManagerName = hotelManagerName;
    }

    public long getHotelManagerContact() {
        return HotelManagerContact;
    }

    public void setHotelManagerContact(long hotelManagerContact) {
        HotelManagerContact = hotelManagerContact;
    }
}
