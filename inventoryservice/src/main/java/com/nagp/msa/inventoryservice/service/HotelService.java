package com.nagp.msa.inventoryservice.service;

import com.nagp.msa.inventoryservice.model.HotelDetails;
import com.nagp.msa.inventoryservice.model.HotelManagerDetails;
import com.nagp.msa.inventoryservice.model.HotelRoomDetails;
import com.nagp.msa.inventoryservice.model.ServiceProviderDetails;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private RestTemplate restTemplate;
    private List<HotelDetails> hotelDetailsList = new ArrayList<>();
    public HotelService() {
        hotelDetailsList.add(new HotelDetails(1,"flora","haryana",
                new HotelRoomDetails(10,"flora",true),
                new HotelManagerDetails("testHotelManager",1234,
                        "flora"),5,1000,12343
        ));
        hotelDetailsList.add(new HotelDetails(2,"fab","gurgaon",
                new HotelRoomDetails(20,"fab",true),
                new HotelManagerDetails("testHotelManager12",123455,
                        "fab"),5,1500,12343
        ));
    }
    public List<HotelDetails>getAllHotelList(){
        //System.out.println(hotelDetailsList.get(0));
        return hotelDetailsList;
    }
    public ServiceProviderDetails getServiceProviderDetails(String userName){
        String baseUrl = eurekaClient.getNextServerFromEureka("profilemanagement", false)
                .getHomePageUrl() + "profile" + "/user/"+ userName;
        ResponseEntity<ServiceProviderDetails> response = restTemplate.exchange(baseUrl, HttpMethod.GET,
                null,ServiceProviderDetails.class);
        return response.getBody();
    }
    public String addHotelDetails(String userName, HotelDetails hotelDetails){
        ServiceProviderDetails serviceProviderDetails = getServiceProviderDetails(userName);
        if (serviceProviderDetails.isLoggedIn()){
            //System.out.println(serviceProviderDetails.getScope());
            if (serviceProviderDetails.getScope().equals("HotelProvider")){
                hotelDetails.getHotelManagerDetails().setHotelName(hotelDetails.getHotelName());
                hotelDetails.getRooms().setHotelName(hotelDetails.getHotelName());
                hotelDetailsList.add(hotelDetails);
                return "hotel added";
            }return "user can't add hotels";
        }return "user need to be logged IN";
    }
    public HotelDetails getHotelDetails( String hotelName){
        Optional<HotelDetails> result = getAllHotelList().stream()
                .filter(c -> hotelName.equals(c.getHotelName())).findFirst();
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }
    public String updateHotelRooms(HotelRoomDetails hotelRoomDetails){
        HotelDetails temphotelDetails = getHotelDetails(hotelRoomDetails.getHotelName());
        if(temphotelDetails!=null){
            int hotelIndex = getAllHotelList().indexOf(temphotelDetails);
            if(hotelRoomDetails.getRooms()==0){
                temphotelDetails.getRooms().setRooms(hotelRoomDetails.getRooms());
                temphotelDetails.getRooms().setAvailable(false);
                hotelDetailsList.set(hotelIndex,temphotelDetails);
                return "rooms updated";
            }
            temphotelDetails.getRooms().setAvailable(true);
            temphotelDetails.getRooms().setRooms(hotelRoomDetails.getRooms());
            hotelDetailsList.set(hotelIndex,temphotelDetails);
            return "rooms updated";

        }return "hotel is not present";

    }
    public HotelDetails getHotelByHotelId(int id){
        return hotelDetailsList.get(id);
    }
}
