package com.tishina.dao;

import com.tishina.model.Arrival;

import java.util.Collection;
import java.util.Date;

public interface ArrivalDAO {
    Collection<Arrival> getAllArrivals();
    Collection<Arrival> getArrivalsByDate(String from, String to);
}
