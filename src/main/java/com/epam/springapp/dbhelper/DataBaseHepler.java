package com.epam.springapp.dbhelper;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcOperations;

public class DataBaseHepler {

    public static void initDataBase(final ApplicationContext applicationContext) {
        JdbcOperations jdbcOperations = (JdbcOperations) applicationContext.getBean("jdbcTemplate");

        jdbcOperations.update("CREATE TABLE IF NOT EXISTS 'USER' (" + //
                "'ID' int(11) NOT NULL AUTO_INCREMENT, " + //
                "'USERNAME' varchar(45) DEFAULT NULL, " + //
                "'USERMAIL' varchar(45) DEFAULT NULL, " + //
                "'BIRTHDATE' TIMESTAMP, " + //
                "PRIMARY KEY ('ID'), " + //
                "UNIQUE KEY 'ID_UNIQUE' ('ID'), " + //
                "UNIQUE KEY 'USERMAIL' ('USERMAIL')" + //
                ") ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8");

        jdbcOperations.update("CREATE TABLE IF NOT EXISTS 'EVENT' (" + //
                "'NAME' varchar(100) NOT NULL, " + //
                "'DATE' varchar(100) DEFAULT NULL, " + //
                "'PRICE' varchar(45) DEFAULT NULL, " + //
                "'RATING' varchar(45) DEFAULT NULL, " + //
                "PRIMARY KEY ('NAME'), " + //
                "UNIQUE KEY 'name_UNIQUE' ('NAME') " + //
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8");

        jdbcOperations.update("CREATE TABLE IF NOT EXISTS 'TICKETDATA' (" + //
                "'USER' varchar(200) NOT NULL, " + //
                "'EVENT' varchar(200) DEFAULT NULL, " + //
                "PRIMARY KEY ('USER'), " + //
                "UNIQUE KEY 'name_UNIQUE' ('USER') " + //
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8");

        jdbcOperations.update("CREATE TABLE IF NOT EXISTS 'INVOCATIONSFOREACHEVENT' (" + //
                "'EVENTNAME' varchar(200) NOT NULL, " + //
                "'COUNT' double DEFAULT NULL, " + //
                "PRIMARY KEY ('EVENTNAME'), " + //
                "UNIQUE KEY 'name_UNIQUE' ('EVENTNAME') " + //
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8");

        jdbcOperations.update("CREATE TABLE IF NOT EXISTS 'TICKETSNUMBERFOREACHUSER' (" + //
                "'USERNAME' varchar(200) NOT NULL, " + //
                "'ID' double DEFAULT NULL, " + //
                "PRIMARY KEY ('USERNAME'), " + //
                "UNIQUE KEY 'name_UNIQUE' ('USERNAME') " + //
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8");

        jdbcOperations.update("CREATE TABLE IF NOT EXISTS 'BIRTHDAYDISCOUNTINVOCATIONS' (" + //
                "'USERNAME' varchar(200) NOT NULL, " + //
                "'COUNT' double DEFAULT NULL, " + //
                "PRIMARY KEY ('USERNAME'), " + //
                "UNIQUE KEY 'name_UNIQUE' ('USERNAME') " + //
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8");

        jdbcOperations.update("CREATE TABLE IF NOT EXISTS 'EVERYTENTHTICKETDISCOUNTINVOCATIONS' (" + //
                "'USERNAME' varchar(200) NOT NULL, " + //
                "'COUNT' double DEFAULT NULL, " + //
                "PRIMARY KEY ('USERNAME'), " + //
                "UNIQUE KEY 'name_UNIQUE' ('USERNAME') " + //
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8");
    }
}
