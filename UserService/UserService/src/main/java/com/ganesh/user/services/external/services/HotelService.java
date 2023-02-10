package com.ganesh.user.services.external.services;

import com.ganesh.user.services.model.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
    @GetMapping("/hotel/{hotelId}")
    public Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
