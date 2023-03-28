package com.nagp.msa.searchservice.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nagp.msa.searchservice.model.HotelDetails;
import com.nagp.msa.searchservice.model.SearchHotelParams;
import com.nagp.msa.searchservice.model.UserSearchHistory;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private RestTemplate restTemplate;

    List<UserSearchHistory> userSearchHistories = new ArrayList<>();
    HashMap<String,List<HotelDetails>> userWishListMap = new HashMap<>();

    public HashMap<String,List<HotelDetails>> getUserWishListMap(){
        return userWishListMap;
    }
    public String userHotelWishList(String userName,int hotelID){
        boolean userStatus = getUserLoginStatus(userName);
        if(userStatus){
            String baseUrl = eurekaClient.getNextServerFromEureka("inventoryservice", false)
                    .getHomePageUrl() + "inventory" + "/get/hotel/"+ hotelID;
            ResponseEntity<Object> response = restTemplate.exchange(baseUrl, HttpMethod.GET,
                    null,Object.class);
            if(response.getBody()!=null){
                Object obj = response.getBody();
                String hotelData = obj.toString();
                JsonObject jsonObject = new Gson().fromJson(hotelData, JsonObject.class);
                HotelDetails hotelDetails = setHotelDetailsProp(jsonObject);
                if(userWishListMap.get(userName)!=null){
                    List<HotelDetails> hotelDetailsList = userWishListMap.get(userName);
                    hotelDetailsList.add(hotelDetails);
                    userWishListMap.put(userName,hotelDetailsList);
                }else{
                    List<HotelDetails> hotelDetailsList = new ArrayList<>();
                    hotelDetailsList.add(hotelDetails);
                    userWishListMap.put(userName,hotelDetailsList);
                }
            }
            return "added into wishlist";
        }
        return "user Needs to be logged IN";
    }
    public boolean getUserLoginStatus(String username){
        String baseUrl = eurekaClient.getNextServerFromEureka("profilemanagement", false)
                .getHomePageUrl() + "profile" + "/login/status/"+ username;
        ResponseEntity<Boolean> response = restTemplate.exchange(baseUrl,HttpMethod.GET,
                null,Boolean.class);
        return  response.getBody();
    }
    public List<UserSearchHistory> getUserMetrics(){
        return userSearchHistories;
    }
    public HotelDetails setHotelDetailsProp(JsonObject jsonObject){
        HotelDetails hotelDetails = new HotelDetails();
        hotelDetails.setHotelID(jsonObject.get("hotelID").getAsInt());
        hotelDetails.setHotelName(jsonObject.get("hotelName").getAsString());
        hotelDetails.setAddress(jsonObject.get("address").getAsString());
        hotelDetails.setRatings(jsonObject.get("rating").getAsInt());
        hotelDetails.setHotelContact(jsonObject.get("contact").getAsLong());
        hotelDetails.setHotelPrice(jsonObject.get("price").getAsInt());
        Object tempObj = jsonObject.get("hotelroomDetails").getAsJsonObject();
        String hotelRoomDetails = tempObj.toString();
        JsonObject hotelRoomDetailsObj = new Gson().fromJson(hotelRoomDetails, JsonObject.class);
        hotelDetails.setRooms(hotelRoomDetailsObj.get("rooms").getAsInt());
        hotelDetails.setAvailable(hotelRoomDetailsObj.get("available").getAsBoolean());

        return hotelDetails;
    }
    public List<HotelDetails> getAllHotelsList(String userName,SearchHotelParams searchHotelParams){
        List<HotelDetails> hotelDetailsList = new ArrayList<>();
        String baseUrl = eurekaClient.getNextServerFromEureka("inventoryservice", false)
                .getHomePageUrl() + "inventory" + "/list/hotel";
        ResponseEntity<List> response = restTemplate.exchange(baseUrl, HttpMethod.GET,
                null,List.class);
        for (Object obj: response.getBody()
             ) {
            String hotelData = obj.toString();
            JsonObject jsonObject = new Gson().fromJson(hotelData, JsonObject.class);
            HotelDetails hotelDetails = setHotelDetailsProp(jsonObject);
            if(hotelDetails.isAvailable()){
                if(hotelDetails.getAddress().equals(searchHotelParams.getCity())) {
                    if (hotelDetails.getRooms()>=searchHotelParams.getRooms()) {
                        hotelDetailsList.add(hotelDetails);
                    }
                }
            }
        }
        if(userName!=null){
            userSearchHistories.add(new UserSearchHistory(userName,hotelDetailsList));
        }
        return hotelDetailsList;

    }
}
