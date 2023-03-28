package com.nagp.msa.hotelbooking.controller;

import com.nagp.msa.hotelbooking.model.*;
import com.nagp.msa.hotelbooking.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("hotel")
public class HotelBookingController {
    private BookingService bookingService;
    public HotelBookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    @GetMapping("/booking/user")
    public Map<String, List<BookingDetailsUser>> getUserBookings(){
        return bookingService.getUserBooking();
    }
    @PostMapping("add/booking/{userName}")
    public String addBooking(@PathVariable("userName") String userName, @RequestBody HotelBookingParams
                             hotelBookingParams){
        return bookingService.addBooking(userName,hotelBookingParams);
    }
    @DeleteMapping("cancel/booking/{username}/{bookingId}")
    public String cancelBooking(@PathVariable("username")String userName,
                                @PathVariable("bookingId")int bookingId){
        return bookingService.cancelBooking(userName,bookingId);
    }

}
