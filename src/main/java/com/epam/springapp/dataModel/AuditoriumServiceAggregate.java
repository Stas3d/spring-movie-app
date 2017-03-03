package com.epam.springapp.dataModel;

import com.epam.springapp.apploger.AppLog;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Stanislav_Kryzhanovs;
 */

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AuditoriumServiceAggregate {

    @Autowired
    private AppLog appLoger;

    @Getter
    @Setter
    @NonNull
    private Event event;

    @Getter
    @Setter
    @NonNull
    private Auditorium auditorium;

    @Getter
    @Setter
    @NonNull
    private String date;
}
