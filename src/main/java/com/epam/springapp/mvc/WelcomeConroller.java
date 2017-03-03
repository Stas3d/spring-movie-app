package com.epam.springapp.mvc;

import com.epam.springapp.booking.BookingServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class WelcomeConroller extends AbstractController {
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ModelAndView model = new ModelAndView("WelcomePage");
        model.addObject("msg", "hello world");

        return model;
    }

    @Autowired
    private final BookingServiceManager bookingServiceManager;

    @Autowired
    public WelcomeConroller(BookingServiceManager bookingServiceManager) {
        this.bookingServiceManager = bookingServiceManager;
    }
    //
    // @RequestMapping(method = RequestMethod.GET)
    // public Map<String, Appointment> get() {
    // return appointmentBook.getAppointmentsForToday();
    // }

}
