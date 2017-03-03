package com.epam.springapp.booking;

import com.epam.springapp.dataModel.*;

import java.util.Date;
import java.util.List;

/**
 * @author Stanislav_Kryzhanovs
 */
public interface BookingServiceManager {

    @Deprecated
    long getTicketPrice(String eventName, String date, String time, Seats seats, User user);

    Ticket bookTicket(User user, Event event);

    List<Ticket> getTicketsForEvent(Event event, Date date);

    List<Ticket> getAllTickets();

    void printAllTickets();


}
