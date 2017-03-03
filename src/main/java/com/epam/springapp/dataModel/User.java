package com.epam.springapp.dataModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Stanislav_Kryzhanovs;
 */

@Entity
@Table(name = "USER")
@NoArgsConstructor
@ToString(includeFieldNames = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class User {

    @Id
    @Column(name = "ID", nullable = false)
    @Getter
    private long userId;

    @Column(name = "USERNAME", nullable = false, length = 255, unique = false)
    @Getter
    private String userName;

    @Column(name = "USERMAIL", nullable = false)
    @Getter
    private String userMail;

    @Column(name = "BIRTHDATE", nullable = false)
    @Getter
    private String birthDate;
}
