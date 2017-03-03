package com.epam.springapp.mvc;


import com.epam.springapp.dataModel.Event;
import com.epam.springapp.exception.mvcexception.*;

import com.epam.springapp.event.EventServiceManager;
import com.epam.springapp.exception.NoEventException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

// 1) resource is identified by URI  !  maaping => ierarhy
// 3) standart operation scope CRUD !
// 4) not resource but it representation
// 5) stateless (not saving its state between calls)


// HTTP MESSAGE CONVERTER

@Controller
@RequestMapping(value = "/events")
public class EventsController {

    @Autowired
    private EventServiceManager eventServiceManager;

    @ResponseBody
    @RequestMapping(value = "/getAllEvents", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Event> receiveAllvents() {
        return eventServiceManager.getAllEvents();
    }

    @ResponseBody
    @RequestMapping(value = "/getEventByName/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Event receiveEventByName(@PathVariable String name) {
        return eventServiceManager.getEventByName(name);
    }

    @ResponseBody
    @RequestMapping(value = "/removeEvent/{name}", method = RequestMethod.GET) // method = RequestMethod.POST
    @ResponseStatus(HttpStatus.OK)
    public boolean removeEventByName(@PathVariable String name) throws NoEventException {
        if (name == null) throw new OrderNotFoundException();
        return eventServiceManager.removeEvent(name);
    }
}
