package com.epam.springapp.apploger;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j
@Service("logging")
public class AppLog {
    private static final String NULL_DATA_ROW_RECEIVED = "NULL DATA ROW RECEIVED";
    private static final String INIT_LOGGER = "=======Init logger=======";

    public static void logEvent(final String object) {
        final String output = (object != null) ? object : NULL_DATA_ROW_RECEIVED;
        System.out.println(output);
        log.debug(output);
    }

    private static void init() throws IOException {
        System.out.println(INIT_LOGGER);
    }
}
