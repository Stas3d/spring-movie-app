package com.epam.springapp.event;

import com.epam.springapp.apploger.AppLog;
import com.epam.springapp.auditorium.AuditoriumService;
import com.epam.springapp.dataModel.*;
import com.epam.springapp.exception.NoEventException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Stanislav_Kryzhanovs
 */

@Service("eventService")
public class EventServiceManagerImpl implements EventServiceManager {

    private static final String ALREADY_EXISTING_USER = "Already existing user [%s]";
    private static final String EVENT_REMOVED = "Event [%s] was successfully removed";
    private static final String NO_EVENT_FOUND = "No event found for Event [%s]";

    @Setter
    private List<List<String>> eventData;

    @Setter
    private static List<Event> events = new ArrayList<>();

    @Autowired
    @Setter
    private AppLog appLogger;

    @Autowired
    @Setter
    private AuditoriumService auditoriumService;

//    @Autowired
//    private JdbcOperations jdbcOperations;

    public void createEvent(final String name, final String date, final long price, final Rating rating) {
        if (events != null && name != null) {
            boolean isEventExists = events
                    .stream()
                    .anyMatch(e
                            -> name.equals(e.getName()));
            if (isEventExists) {
                appLogger.logEvent(String.format(ALREADY_EXISTING_USER, name));
                return;
            }
            final Event event = new Event(name, date, price, rating);
            events.add(event);
            // int result = jdbcOperations.update("INSERT into EVENT(NAME,DATE,PRICE,RATING) VALUES (?,?,?,?)",
            // name, date, price, rating.toString());
        }
    }

    public boolean removeEvent(final String name) throws NoEventException {
        // jdbcOperations.update("DELETE FROM EVENT WHERE NAME =?", name);
        final Event event = events
                .stream()
                .filter(el -> name.equals(el.getName()))
                .findFirst()
                .orElseThrow(() -> new NoEventException());
        events.remove(event);
        appLogger.logEvent(String.format(EVENT_REMOVED, event));
        return true;
    }

//    public void removeEvent(final String name) {
//        if (events != null && name != null) {
//            // jdbcOperations.update("DELETE FROM EVENT WHERE NAME =?", name);
//            final Event event = events
//                    .stream()
//                    .filter(el
//                            -> name.equals(el.getName()))
//                    .findFirst()
//                    .get();
//            events.remove(event);
//            appLogger.logEvent(String.format(EVENT_REMOVED, event));
//        }
//    }

    public Event getEventByName(final String name) {
        return events
                .stream()
                .filter(event
                        -> name.equals(event.getName()))
                .findFirst()
                .orElseThrow(()
                        -> new NoEventException(String.format(NO_EVENT_FOUND, name)));
    }

    public List<Event> getAllEvents() {
        return events;
    }

    public boolean assignAuditoriumForEvent(final Event event, final Auditorium auditorium) {
        auditoriumService.assignAuditorium(event, auditorium);
        return true;
    }

    @Deprecated
    public void printAllEvents() {
        events.forEach(event ->
                appLogger.logEvent(event.toString()));
        appLogger.logEvent("\n");
    }

    /**
     * This method adds default Production events to App, called by
     * @link App.addInitialUsersToApplication() from eventsData.xml
     */
    public void addInitialEvents() { //
        eventData.forEach(eventsRow -> //
                events.add(new Event( //
                        eventsRow.get(0), //
                        eventsRow.get(1), //
                        Integer.parseInt(eventsRow.get(2)), //
                        Rating.LOW //
                )));
    }
}
