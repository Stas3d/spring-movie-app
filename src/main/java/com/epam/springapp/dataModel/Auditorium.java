package com.epam.springapp.dataModel;

import lombok.NonNull;

public enum Auditorium {

    A95("A95"), //
    A96("A96"), //
    A97("A97"), //
    A98("A98"), //
    A99("A99"), //
    A100("A100"), //
    A101("A101"), //
    A102("A102"), //
    A103("A103"), //
    A104("A104"), //
    A105("A105"), //
    A200("A200");//

    private String string;

    Auditorium(@NonNull String name) {
        string = name;
    }

    @Override
    public String toString() {
        return string;
    }
}
