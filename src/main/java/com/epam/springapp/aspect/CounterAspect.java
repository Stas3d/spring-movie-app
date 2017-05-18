package com.epam.springapp.aspect;

import com.epam.springapp.apploger.AppLog;
import com.epam.springapp.dataModel.User;
import lombok.Setter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;


import java.util.HashMap;

@Aspect
public class CounterAspect {
    private static final String NUMBER_OF_BOOKED_TICKETS = "For User [%s] number of booked tickets was [%s] ";
    private static final String INVOCATIONS_FOR_EVENT = "For event [%s] there was [%s] invocations";
    private static final String BOOK_TICKET_WAS_NUMBER_TIMES = "bookTicket() was called [%s] times";

    private HashMap<String, Integer> invocationsForEachEvent = new HashMap<>();
    private HashMap<String, Integer> ticketsNumberForEachUser = new HashMap<>();

    private int countBookTicketMethodCalled;

    @Autowired
    @Setter
    private AppLog appLoger;

    @Autowired
    private JdbcOperations jdbcOperations;

    @AfterReturning(pointcut = "execution(* com.epam.springapp.event.EventServiceManager.getEventByName(..))",
            returning = "returnedEvent")
    public void countInvocationsNumberForEvent(final Object returnedEvent) {
        final String event = returnedEvent.toString();
        if (!invocationsForEachEvent.containsKey(event)) {
            invocationsForEachEvent.put(event, 0);
        }
        invocationsForEachEvent.put(event, (invocationsForEachEvent.get(event) + 1));
//        int result = jdbcOperations.update("INSERT into INVOCATIONSFOREACHEVENT(EVENTNAME,COUNT) VALUES (?,?)",
//                event, (invocationsForEachEvent.get(event) + 1));
    }

    public void showInvocationsNumberForEachEvent() {
        invocationsForEachEvent
                .keySet()
                .forEach(key ->
                        appLoger.logEvent(String.format(INVOCATIONS_FOR_EVENT, key, invocationsForEachEvent.get(key))));
    }

    @Before("execution(* com.epam.springapp.booking.BookingServiceManager.bookTicket(..))")
    public void countNumberOfTimesBookTicketCalled() {
        countBookTicketMethodCalled++;
    }

    public int getCountBookTicketMethodCalled() {
        appLoger.logEvent(String.format(BOOK_TICKET_WAS_NUMBER_TIMES, countBookTicketMethodCalled));
        return countBookTicketMethodCalled;
    }

    @Before("execution(* com.epam.springapp.booking.BookingServiceManager.bookTicket(..))")
    public void countNumberOfTimesTicketBookedForEachUser(final JoinPoint joinPoint) {
        final Object[] signatureArgs = joinPoint.getArgs();
        final User user = (User) signatureArgs[0];
        final String userName = user.getUserName();
        if (!ticketsNumberForEachUser.containsKey(userName)) {
            ticketsNumberForEachUser.put(userName, 0);
        }
        ticketsNumberForEachUser.put(userName, (ticketsNumberForEachUser.get(userName) + 1));
//        int result = jdbcOperations.update("INSERT into TICKETSNUMBERFOREACHUSER(USERNAME,ID) VALUES (?,?)",
//                userName, (ticketsNumberForEachUser.get(userName) + 1));
    }

    public void showBookTicketCountForAllUsers() {
        ticketsNumberForEachUser
                .keySet()
                .forEach(key ->
                        appLoger.logEvent(String.format(NUMBER_OF_BOOKED_TICKETS, key, ticketsNumberForEachUser.get(key))));
    }
}
