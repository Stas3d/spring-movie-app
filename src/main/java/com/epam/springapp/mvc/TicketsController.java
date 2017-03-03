package com.epam.springapp.mvc;

import com.epam.springapp.booking.BookingServiceManager;
import com.epam.springapp.dataModel.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping(value = "/tickets")
public class TicketsController {

    @Autowired
    private BookingServiceManager bookingServiceManager;

    @ResponseBody
    @RequestMapping(value = "/getAllTickets", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Ticket> receiveAllTickets() {
        return bookingServiceManager.getAllTickets();
    }


    // ToDO implement  ticket booking operations(required)
}
