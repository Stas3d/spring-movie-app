package com.epam.springapp.discount;

import com.epam.springapp.dataModel.Event;
import com.epam.springapp.dataModel.User;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Stanislav_Kryzhanovs
 */

@Service("discountServiceManager")
public class DiscountServiceImpl implements DiscountService {

    private User user;
    private Event event;
    private String date;
    private HashMap<String, Integer> userDiscountCount = new HashMap<>();

    public long getTotalDiscount(final User user, final Event event, final String date) {
        this.user = user;
        this.event = event;
        this.date = date;
        final int birthdayDiscount = getBirthdayDiscountStrategy();
        final int every10thTicketDiscount = getEvery10thTicketDiscount();
        return Math.max(birthdayDiscount, every10thTicketDiscount);
    }

    public int getBirthdayDiscountStrategy() {
        return date.substring(0, 4).equals(getCurrentDateAtMmDdFormat()) ? 5 : 0;
    }

    public int getEvery10thTicketDiscount() {
        final String userName = user.getUserName();
        if (!userDiscountCount.containsKey(userName)) {
            userDiscountCount.put(userName, 0);
        }
        userDiscountCount.put(userName, (userDiscountCount.get(userName) + 1));
        return userDiscountCount.get(userName) % 10 != 0 ? 0 : 50;
    }

    private String getCurrentDateAtMmDdFormat() {
        final DateFormat dateFormat = new SimpleDateFormat("MM/dd");
        final Date date = new Date();
        return dateFormat.format(date);
    }
}
