package com.nagp.msa.searchservice.controller;

import com.nagp.msa.searchservice.model.HotelDetails;
import com.nagp.msa.searchservice.model.SearchHotelParams;
import com.nagp.msa.searchservice.model.UserSearchHistory;
import com.nagp.msa.searchservice.service.SearchService;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("search")
public class SearchController {
    private final SearchService searchService;
    public SearchController(SearchService searchService){
        this.searchService = searchService;

    }

    @GetMapping("/hotels")
    public List<HotelDetails> getAllHotelsList(@QueryParam ("userName")String userName,@RequestBody SearchHotelParams searchHotelParams){
        return searchService.getAllHotelsList(userName,searchHotelParams);
    }
    @GetMapping("/metrics/data")
    public List<UserSearchHistory> getMetricsData(){
        return searchService.getUserMetrics();
    }
    @PostMapping("add/wishlist/{userName}/{hotelId}")
    public String addWishlist(@PathVariable("userName") String userName,@PathVariable("hotelId") int hotelID){
        return searchService.userHotelWishList(userName,hotelID);
    }
    @GetMapping("get/wishlist")
    public HashMap<String, List<HotelDetails>> getUserWishlist(){
        return searchService.getUserWishListMap();
    }

    @GetMapping("/flights")
    public String flightView(){
        return "hello from search service";
    }
}
