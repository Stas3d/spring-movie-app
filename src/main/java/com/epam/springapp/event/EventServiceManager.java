package com.epam.springapp.event;

import com.epam.springapp.dataModel.Auditorium;
import com.epam.springapp.dataModel.Event;
import com.epam.springapp.dataModel.Rating;
import com.epam.springapp.exception.NoEventException;

import java.util.List;

/**
 * @author Stanislav_Kryzhanovs;
 */
public interface EventServiceManager {

    void createEvent(String name, String date, long price, Rating rating);

    boolean removeEvent(String name) throws NoEventException;

    Event getEventByName(String name);

    List<Event> getAllEvents();

    boolean assignAuditoriumForEvent(Event event, Auditorium auditorium);

    @Deprecated
    void printAllEvents();

    void addInitialEvents();
}
