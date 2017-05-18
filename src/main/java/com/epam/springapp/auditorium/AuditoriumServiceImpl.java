package com.epam.springapp.auditorium;

import com.epam.springapp.apploger.AppLog;
import com.epam.springapp.dataModel.Auditorium;
import com.epam.springapp.dataModel.Event;
import com.epam.springapp.dataModel.EventRoomDataModel;
import com.epam.springapp.exception.NoAuditoriumException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("auditorium")
public class AuditoriumServiceImpl implements AuditoriumService {

    private static final String CANT_ASSIGN_AUDITORIUM_FOR_EVENT = "Can't to assign such Auditorium for event [%s] ";
    private static final String AUDITORIUM_NOT_FOUND = "Auditorium [%s] was not found at configuration properties";
    private List<EventRoomDataModel> eventRoomDataModelList = new ArrayList<>();

    @Setter
    private AppLog appLog;

    @Setter
    private String auditoriums;

    @Autowired
    private AppLog appLoger;

    public List<String> getAuditoriums() {
        return null;
    }

    public void getSeatsNumber() {
    }

    public List<String> getVipSeats(final Auditorium auditorium) {
        List<String> myList = new ArrayList<>(Arrays.asList(String.valueOf(auditoriums).split(",")));
        appLoger.logEvent(auditoriums);
        return null;
    }

    public boolean assignAuditorium(final Event event, final Auditorium auditorium) {
        checkIfAuditoriumIsValid(auditorium);
        final boolean isBusy = eventRoomDataModelList.stream()
                .anyMatch(it ->
                        (event.toString().equals(it.getEvent().toString())
                                && auditorium.toString().equals(it.getAuditorium().toString())));
        if (isBusy) {
            appLog.logEvent(String.format(CANT_ASSIGN_AUDITORIUM_FOR_EVENT, event.toString()));
            return true; // TODO return FALSE ????
        }
        eventRoomDataModelList.add(new EventRoomDataModel(event, auditorium));
        return true;
    }

    private void checkIfAuditoriumIsValid(final Auditorium auditorium) {
//        List<String> myList = new ArrayList<>(Arrays.asList(String.valueOf(auditoriums).split(",")));
        Arrays.asList(String.valueOf(auditoriums).split(","))
                .stream()
                .filter( item ->
                        item.equals(auditorium.toString()))
                .findFirst()
                .orElseThrow(
                        () ->
                                new NoAuditoriumException(String.format(AUDITORIUM_NOT_FOUND, auditorium.toString()))
                );
    }
}
