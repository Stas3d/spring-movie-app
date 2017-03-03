package com.epam.springapp.dataModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Stanislav_Kryzhanovs
 */

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class EventRoomDataModel {

    @Getter
    @Setter
    private Event event;

    @Getter
    @Setter
    private Auditorium auditorium;
}
