package com.ganesh.hotel.service;

import com.ganesh.hotel.model.Hotel;

import java.util.List;

public interface HotelService {
    Hotel create(Hotel hotel);
    List<Hotel> getAll();
    Hotel get(String id);
}
