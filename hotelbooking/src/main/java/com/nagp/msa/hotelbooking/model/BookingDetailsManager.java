package com.nagp.msa.hotelbooking.model;

public class BookingDetailsManager {

    private int bookingId;
    private int rooms;
    private int deductedMoney;
    private String checkInDate;
    private String checkOutDate;
    private String customerName;
    private long customerContact;

    @Override
    public String toString() {
        return "BookingDetailsManager{" +
                "bookingId=" + bookingId +
                ", rooms=" + rooms +
                ", deductedMoney=" + deductedMoney +
                ", checkInDate='" + checkInDate + '\'' +
                ", checkOutDate='" + checkOutDate + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerContact=" + customerContact +
                '}';
    }

    public BookingDetailsManager(int bookingId, int rooms, int deductedMoney, String checkInDate, String checkOutDate, String customerName, long customerContact) {

        this.bookingId = bookingId;
        this.rooms = rooms;
        this.deductedMoney = deductedMoney;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.customerName = customerName;
        this.customerContact = customerContact;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(long customerContact) {
        this.customerContact = customerContact;
    }
}
