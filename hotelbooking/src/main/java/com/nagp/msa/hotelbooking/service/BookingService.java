package com.nagp.msa.hotelbooking.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nagp.msa.hotelbooking.model.*;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BookingService {
    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private RestTemplate restTemplate;
    private int count=0;
    Map<String,List<BookingDetailsUser>> userBookingMap= new HashMap<String,List<BookingDetailsUser>>();

    public UserDetails setUserDetailsProp(Object obj){
        String userData = obj.toString();
        JsonObject jsonObject = new Gson().fromJson(userData, JsonObject.class);
        UserDetails userDetails = new UserDetails();
        userDetails.setName(jsonObject.get("name").getAsString());
        userDetails.setUserContact(jsonObject.get("contact").getAsLong());
        userDetails.setUserAddress(jsonObject.get("address").getAsString());
        Object tempObj = jsonObject.get("walletMoney").getAsJsonObject();
        String userWalletDetails = tempObj.toString();
        JsonObject userWalletDetailsObj = new Gson().fromJson(userWalletDetails, JsonObject.class);
        userDetails.setWalletMoney(userWalletDetailsObj.get("money").getAsInt());
        return userDetails;
    }

    public HotelDetails setHotelProp(Object obj){
        String hotelData = obj.toString();
        JsonObject jsonObject = new Gson().fromJson(hotelData, JsonObject.class);
        HotelDetails hotelDetails = new HotelDetails();
        hotelDetails.setHotelName(jsonObject.get("hotelName").getAsString());
        hotelDetails.setAddress(jsonObject.get("address").getAsString());
        hotelDetails.setPrice(jsonObject.get("price").getAsInt());
        hotelDetails.setRating(jsonObject.get("rating").getAsInt());
        hotelDetails.setHotelContact(jsonObject.get("contact").getAsLong());
        Object tempObj = jsonObject.get("hotelManagerDetails").getAsJsonObject();
        String hotelManagerDetails = tempObj.toString();
        JsonObject hotelRoomDetailsObj = new Gson().fromJson(hotelManagerDetails, JsonObject.class);
        hotelDetails.setManagerName(hotelRoomDetailsObj.get("hotelManagerName").getAsString());
        hotelDetails.setManagerContact(hotelRoomDetailsObj.get("hotelManagerContact").getAsLong());
        tempObj = jsonObject.get("hotelroomDetails").getAsJsonObject();
        hotelManagerDetails = tempObj.toString();
        hotelRoomDetailsObj = new Gson().fromJson(hotelManagerDetails, JsonObject.class);
        hotelDetails.setAvailable(hotelRoomDetailsObj.get("available").getAsBoolean());
        hotelDetails.setRooms(hotelRoomDetailsObj.get("rooms").getAsInt());
        return hotelDetails;
    }
    public boolean getUserLoginStatus(String username){
        String baseUrl = eurekaClient.getNextServerFromEureka("profilemanagement", false)
                .getHomePageUrl() + "profile" + "/login/status/"+ username;
        ResponseEntity<Boolean> response = restTemplate.exchange(baseUrl,HttpMethod.GET,
                null,Boolean.class);
        return  response.getBody();
    }
    public Object getHotelDetails(int hotelID){
        String baseUrl = eurekaClient.getNextServerFromEureka("inventoryservice", false)
                .getHomePageUrl() + "inventory" + "/get/hotel/"+hotelID;
        ResponseEntity<Object> response = restTemplate.exchange(baseUrl, HttpMethod.GET,
                null,Object.class);
        //System.out.println(response.getBody());
        return response.getBody();
    }
    public Object getHotelDetailsByHotelName(String hotelName){
        String baseUrl = eurekaClient.getNextServerFromEureka("inventoryservice", false)
                .getHomePageUrl() + "inventory" + "/get/hotel?hotelName="+hotelName;
        ResponseEntity<Object> response = restTemplate.exchange(baseUrl, HttpMethod.GET,
                null,Object.class);
        //System.out.println(response.getBody());
        return response.getBody();
    }
    public Object getUserWalletDetails(String userName){
        String baseUrl = eurekaClient.getNextServerFromEureka("walletservice", false)
                .getHomePageUrl() + "wallet" + "/users/details/"+userName;
        ResponseEntity<Object> response = restTemplate.exchange(baseUrl, HttpMethod.GET,
                null,Object.class);
       // System.out.println(response.getBody());
        return response.getBody();
    }
    public String refundWalletMoney(String userName, int money){
        WalletMoney walletMoney = new WalletMoney(userName,money);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<WalletMoney> requestEntity = new HttpEntity<WalletMoney>(walletMoney, headers);
        String baseUrl = eurekaClient.getNextServerFromEureka("walletservice", false)
                .getHomePageUrl() + "wallet" + "/add";
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.PUT,
                requestEntity,String.class);
        //System.out.println(response.getBody());
        return "yes";
    }
    public String deductWalletMoney(String userName, int money){
        WalletMoney walletMoney = new WalletMoney(userName,money);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<WalletMoney> requestEntity = new HttpEntity<WalletMoney>(walletMoney, headers);
        String baseUrl = eurekaClient.getNextServerFromEureka("walletservice", false)
                .getHomePageUrl() + "wallet" + "/deduct";
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.PUT,
                requestEntity,String.class);
        //System.out.println(response.getBody());
        return "yes";
    }
    public String updateHotelRooms(String hotelName, int rooms){
        HotelRoomUpdate hotelRoomUpdate = new HotelRoomUpdate(rooms,hotelName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<HotelRoomUpdate> requestEntity = new HttpEntity<HotelRoomUpdate>(hotelRoomUpdate, headers);
        String baseUrl = eurekaClient.getNextServerFromEureka("inventoryservice", false)
                .getHomePageUrl() + "inventory" + "/update/hotel/rooms";
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.PUT,
                requestEntity,String.class);
        //System.out.println(response.getBody());
        return "rooms update";
    }
    public Map<String,List<BookingDetailsUser>> getUserBooking(){
        return userBookingMap;
    }
    public String cancelBooking(String userName, int bookingId){
        boolean userStatus = getUserLoginStatus(userName);
        if(userStatus){
            if(userBookingMap.get(userName)!=null){
                List<BookingDetailsUser> bookingDetailsUserList = userBookingMap.get(userName);
                for (BookingDetailsUser bookingUser: bookingDetailsUserList
                ) {
                    if(bookingUser.getBookingId() == bookingId){
                        System.out.println("booking is being canceled");
                        bookingDetailsUserList.remove(bookingUser);
                        System.out.println("refund process initiated");
                        refundWalletMoney(userName,bookingUser.getDeductedMoney());
                        Object hotelData = getHotelDetailsByHotelName(bookingUser.getHotelName());
                        //System.out.println(hotelData);
                        HotelDetails hotelDetails = setHotelProp(hotelData);
                        int updatedRooms = bookingUser.getRooms()+ hotelDetails.getRooms();
                        updateHotelRooms(bookingUser.getHotelName(),updatedRooms);
                        System.out.println("sending msg to manager: booking is canceled for booking id: "+bookingUser.getBookingId());
                        userBookingMap.put(userName,bookingDetailsUserList);
                        break;

                    }else{
                        return "invalid booking id";
                    }
                }
                return "booking canceled";
            }
            return "no booking present";

        }
        return "user needs to be logged In";
    }
    public String addBooking(String userName, HotelBookingParams hotelBookingParams){
        boolean userStatus = getUserLoginStatus(userName);
        if (userStatus){
            Object userWallet = getUserWalletDetails(userName);
            UserDetails userDetails = setUserDetailsProp(userWallet);
            Object hotelData = getHotelDetails(hotelBookingParams.getHotelId());
            HotelDetails hotelDetails = setHotelProp(hotelData);
            if(hotelDetails.isAvailable()){
                if (hotelDetails.getRooms()>=hotelBookingParams.getRooms()){
                    if(hotelDetails.getPrice()<=userDetails.getWalletMoney()){
                        int updatedRooms = hotelDetails.getRooms()-hotelBookingParams.getRooms();
                        int deductedMoney = hotelDetails.getPrice()*hotelBookingParams.getRooms();
                        System.out.println("money is being deducted");
                        deductWalletMoney(userName,deductedMoney);
                        System.out.println("hotel room is being booked");
                        updateHotelRooms(hotelDetails.getHotelName(),updatedRooms);
                        count = count+1;
                        BookingDetailsUser bookingDetailsUser = new BookingDetailsUser(count,
                                hotelDetails.getHotelName(), hotelBookingParams.getRooms(),
                                deductedMoney,hotelBookingParams.getCheckInDate(),
                                hotelBookingParams.getGetCheckOutDate(),
                                hotelDetails.getManagerName(),hotelDetails.getManagerContact());
                        BookingDetailsManager bookingDetailsManager = new BookingDetailsManager(count,
                                hotelBookingParams.getRooms(),deductedMoney,hotelBookingParams.getCheckInDate(),
                                hotelBookingParams.getGetCheckOutDate(), userDetails.getName(),
                                userDetails.getUserContact());
                        System.out.println("booking details send to hotel manager-->"
                                +bookingDetailsManager.toString());
                        System.out.println("booking details send to customer-->"+
                                bookingDetailsUser.toString());
                        if(userBookingMap.get(userName)!=null){
                            List<BookingDetailsUser>bookingDetailsUsersList = userBookingMap.get(userName);
                            bookingDetailsUsersList.add(bookingDetailsUser);
                            userBookingMap.put(userName,bookingDetailsUsersList);
                        }else{
                            List<BookingDetailsUser>bookingDetailsUserList = new ArrayList<>();
                            bookingDetailsUserList.add(bookingDetailsUser);
                            userBookingMap.put(userName,bookingDetailsUserList);
                        }
                        return "hotel booked";
                    }return "wallet money not enough";
                }return "requested rooms not available";
            }return "hotel not available";
        }
        return "user needs to be logged In";
    }
}
