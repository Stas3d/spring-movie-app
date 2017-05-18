package com.epam.springapp.booking;

import com.epam.springapp.apploger.AppLog;
import com.epam.springapp.dataModel.*;
import com.epam.springapp.discount.DiscountService;
import com.epam.springapp.discount.DiscountServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service("bookingService")
public class BookingServiceManagerImpl implements BookingServiceManager {
    private static final String STRING_LINE = "============================================================================================================\n";
    private static final String TICKETS_STRING_LINE = "                                              Tickets:\n";
    private static final String OBJECT_LINE = "                                                ***                   \n";

    @Getter
    private static List<Ticket> ticketList = new ArrayList<>();

    @Setter
    private AppLog appLog;

    @Setter
    private long value;

//    @Autowired
//    private JdbcOperations jdbcOperations;

//    public void setappLog(AppLog appLog) {
//        this.appLog = appLog;
//    }

    public long getTicketPrice(final String eventName, final String date, final String time, final Seats seats, final User user) {
        return 0;
    }

    public long getTicketPrice(final Event event, final String date, final String time, final Seats seats, final User user) {
        if (event != null) {
            long basePrice = event.getPrice();
            if (seats.isVipSeats(seats.toString())) {
                basePrice = +value;
            }
            final DiscountService discountService = new DiscountServiceImpl();
            final long discount = discountService.getTotalDiscount(user, event, date);
            return basePrice - discount;
        }
        return 0;
    }

    public Ticket bookTicket(final User user, final Event event) {
//        int result = jdbcOperations.update("INSERT into TICKETDATA(USER,EVENT) VALUES (?,?)", user.toString(), event.toString());
        final Ticket ticket = new Ticket(user, event);
        ticketList.add(ticket);
        return ticket;
    }

    /**
     * The Event allready contains information about the data
     */
    public List<Ticket> getTicketsForEvent(final Event event, final Date date) {
        return ticketList
                .stream()
                .filter(item ->
                        item.getEvent().getName().equals(event.getName()))
                .collect(Collectors.toList());
    }

    public List<Ticket> getAllTickets(){
        return ticketList;
    }

    public void printAllTickets() {
        appLog.logEvent(STRING_LINE + TICKETS_STRING_LINE);
        ticketList.stream().forEach(ticket -> {
            appLog.logEvent(ticket.toString());
            appLog.logEvent(OBJECT_LINE);
        });
    }
}
