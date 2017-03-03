package com.epam.springapp.auditorium;

import com.epam.springapp.dataModel.Auditorium;
import com.epam.springapp.dataModel.Event;

import java.util.Date;
import java.util.List;

/**
 * @author Stanislav_Kryzhanovs
 */
public interface AuditoriumService {

    List<String> getAuditoriums();

    void getSeatsNumber();

    List<String> getVipSeats(Auditorium auditorium);

    boolean assignAuditorium(Event event, Auditorium auditorium);
}
