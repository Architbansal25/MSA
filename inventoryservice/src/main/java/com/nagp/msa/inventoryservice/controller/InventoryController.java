package com.nagp.msa.inventoryservice.controller;

import com.nagp.msa.inventoryservice.model.HotelDetails;
import com.nagp.msa.inventoryservice.model.HotelRoomDetails;
import com.nagp.msa.inventoryservice.service.HotelService;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("inventory")
public class InventoryController {
    private final HotelService hotelService;
    public InventoryController( HotelService hotelService){
        this.hotelService = hotelService;
    }

    @GetMapping("/list/hotel")
    public List<HotelDetails> getHotelList(){
        return hotelService.getAllHotelList();
    }
    @GetMapping("/get/hotel/{id}")
    public HotelDetails getHotelByHotelId(@PathVariable("id") int id){
        int hotelId = id - 1;
        return hotelService.getHotelByHotelId(hotelId);
    }
    @PostMapping("add/hotel/{userName}")
    public String addHotel(@PathVariable("userName") String userName,
                           @RequestBody HotelDetails hotelDetails){
        return hotelService.addHotelDetails(userName, hotelDetails);
    }
    @GetMapping("get/hotel")
    public HotelDetails getHotelDetail(@QueryParam("hotelName") String hotelName){
        return hotelService.getHotelDetails(hotelName);
    }
    @PutMapping("update/hotel/rooms")
    public String updateHotelRooms(@RequestBody HotelRoomDetails hotelRoomDetails){
        return hotelService.updateHotelRooms(hotelRoomDetails);
    }


}
