package com.epam.springapp.dataModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Stanislav_Kryzhanovs;
 */

@Entity
@Table(name = "EVENT")

@ToString(includeFieldNames = true)
// @ToString(exclude = "rating")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Event {

    @Id
    @Column(name = "NAME", nullable = false)
    @Getter
    @Setter
    @NonNull
    private String name;

    @Column(name = "DATE", nullable = false, length = 255, unique = false)
    @NonNull
    private String date;

    @Column(name = "PRICE", nullable = false)
    @Getter
    @NonNull
    private long price;

    @Column(name = "RATING", nullable = false)
    @Getter
    @NonNull
    private Rating rating;
}
