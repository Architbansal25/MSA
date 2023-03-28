package com.nagp.msa.searchservice.model;

import java.util.List;

public class UserSearchHistory {
    private String userName;
    private List<HotelDetails> hotelDetailsList;

    public UserSearchHistory(String userName, List<HotelDetails> hotelDetailsList) {
        this.userName = userName;
        this.hotelDetailsList = hotelDetailsList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<HotelDetails> getHotelDetailsList() {
        return hotelDetailsList;
    }

    public void setHotelDetailsList(List<HotelDetails> hotelDetailsList) {
        this.hotelDetailsList = hotelDetailsList;
    }
}
