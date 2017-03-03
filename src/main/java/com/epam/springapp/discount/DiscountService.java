package com.epam.springapp.discount;

import com.epam.springapp.dataModel.Event;
import com.epam.springapp.dataModel.User;

import java.util.Date;

/**
 * @author Stanislav_Kryzhanovs
 */
public interface DiscountService {

    /**
     * returns discount in percentage {0-100%}
     * for each ticket for the user on particular event
     */
    long getTotalDiscount(User user, Event event, String date);

    int getBirthdayDiscountStrategy();

    int getEvery10thTicketDiscount();
}
