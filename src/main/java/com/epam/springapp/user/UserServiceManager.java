package com.epam.springapp.user;

import com.epam.springapp.dataModel.Ticket;
import com.epam.springapp.dataModel.User;

import java.util.List;

public interface UserServiceManager {

    void registerUser(String userName, long userId, String userMail, String birthDate);

    void removeUser(long userId);

    User getUserById(long userId);

    User getUserByEmail(String mail);

    User getUserByName(String name);

    List<Ticket> getBookedTickets(User user);

    List<User> findAllUsers();

    @Deprecated
    void showAllUsers();

    boolean checkIfMailValid(String email);

    boolean checkIfUserExists(String name);

    void addInitialUsers();
}
