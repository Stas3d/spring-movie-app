package com.epam.springapp.user;

import com.epam.springapp.apploger.AppLog;
import com.epam.springapp.dataModel.Ticket;
import com.epam.springapp.dataModel.User;
import com.epam.springapp.exception.NoUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceManagerImpl implements UserServiceManager {

    private static final String EXISTING_USER = "Already existing user [%s]";
    private static final String USER_WAS_REMOVED = "User [%s] was removed";
    private static final String WRONG_MAIL = "wrong or existing mail [%s]";
    private static final String CAN_NOT_FIND_USER = "Can not find user with [%s]";
    private static final String NULL_USER = "User is null";
    private static final String NULL_MAIL = "Mail is null";
    private static final String MAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private static final Pattern EMAIL_REGEX_PATTERN = Pattern.compile(MAIL_REGEX, Pattern.CASE_INSENSITIVE);

    @Getter
    @Setter
    private List<List<String>> testUsers;

    @Getter
    @Setter
//    @InjectInitialAppUsers
    private static List<User> users = new ArrayList<>();

    @Getter
    @Setter
    private static List<Ticket> tickets = new ArrayList<>();

    @Autowired
    @Setter
    private AppLog appLoger;

//    @Autowired
//    private JdbcOperations jdbcOperations;

    // public static void setUsers(final List<User> users) {
    // UserServiceManagerImpl.users = users;
    // }

    // public void setTestUsers(final List testUsers) {
    // this.testUsers = testUsers;
    // }

    // public static List<Ticket> getTickets() {
    // return tickets;
    // }

    // public static void setTickets(final List<Ticket> tickets) {
    // UserServiceManagerImpl.tickets = tickets;
    // }

    public List<User> findAllUsers() {
        return users;
    }


    // TODO Should be redone to exclude void
    public void registerUser(final String userName,
                             final long userId,
                             final String userMail,
                             final String birthDate) {
        if (checkIfMailValid(userMail) && !checkIfUserExists(userMail)) {
            final boolean isExistingUser = users
                    .stream()
                    .anyMatch(user 
                            -> (userId == user.getUserId()));
            if (isExistingUser) {
                appLoger.logEvent(String.format(EXISTING_USER, userId));
                return;
            }
            final User newUser = new User(userId, userName, userMail, birthDate);
            users.add(newUser);
                // int result = jdbcOperations.update("INSERT into USER(USERNAME,ID,USERMAIL,BIRTHDATE) VALUES (?,?,?,?)", userName, userId, userMail, birthDate);
//            }
        }
        appLoger.logEvent(String.format(WRONG_MAIL, userMail));
    }

//    public void registerUser(String userName, long userId, String userMail, String birthDate) {
//        if (checkIfMailValid(userMail) && !checkIfUserExists(userMail)) {
//            if (users != null) {
//                for (User user : users) {
//                    if (userId == user.getUserId()) {
//                        appLoger.logEvent(String.format(EXISTING_USER, user));
//                        return;
//                    }
//                }
//                User newUser = new User(userId, userName, userMail, birthDate);
//                users.add(newUser);
//                // int result = jdbcOperations.update("INSERT into USER(USERNAME,ID,USERMAIL,BIRTHDATE) VALUES
//                // (?,?,?,?)",
//                // userName, userId, userMail, birthDate);
//            }
//        }
//        appLoger.logEvent(String.format(WRONG_MAIL, userMail));
//    }

//    public void removeUser(final long userId) {
//        if (users != null) {
//            // jdbcOperations.update("DELETE FROM USER WHERE id =?", userId);
//            for (User user : users) {
//                if (userId == user.getUserId()) {
//                    users.remove(user);
//                    appLoger.logEvent(String.format(USER_WAS_REMOVED, user));
//                    return;
//                }
//            }
//        }
//    }

    // TODO make this boolean
    public void removeUser(final long userId) {
        if (users != null) {
            // jdbcOperations.update("DELETE FROM USER WHERE id =?", userId);
            final User userToBeDeleted = users.stream()
                    .filter(user -> userId == user.getUserId())
                    .findFirst()
                    .get();
            users.remove(userToBeDeleted);
            appLoger.logEvent(String.format(USER_WAS_REMOVED, userToBeDeleted));
        }
    }

    public User getUserById(final long userId) {
        if (users != null)
            return users.stream()
                    .filter(user -> userId == user.getUserId())
                    .findFirst()
                    .orElseThrow(
                            () -> new NoUserException(String.format(CAN_NOT_FIND_USER, userId)));
        else
            throw new NoUserException(String.format(CAN_NOT_FIND_USER, userId));
    }

//    public User getUserByEmail(String mail) {
//        if ((users != null) && (mail != null)) {
//            for (User user : users) {
//                if (mail.equals(user.getUserMail())) {
//                    return user;
//                }
//            }
//        }
//        throw new NoUserException(String.format(CAN_NOT_FIND_USER, mail));
//    }

    public User getUserByEmail(final String mail) {
        if ((users != null) && (mail != null))
            return users.stream()
                    .filter(m -> mail.equals(m.getUserMail()))
                    .findFirst()
                    .orElseThrow(
                            () -> new NoUserException(String.format(CAN_NOT_FIND_USER, mail)));
        else
            throw new NoUserException(NULL_MAIL);

    }

    public User getUserByName(final String name) {
        if ((users != null) && (name != null))
            return users.stream()
                    .filter(user -> name.equals(user.getUserName()))
                    .findFirst()
                    .orElseThrow(
                            () ->
                                    new NoUserException(String.format(CAN_NOT_FIND_USER, name)));
        else
            throw new NoUserException(NULL_USER);
    }

//    public User getUserByName(final String name) {
//        if ((users != null) && (name != null)) {
//            for (User user : users) {
//                if (name.equals(user.getUserName())) {
//                    return user;
//                }
//            }
//        }
//        throw new NoUserException(String.format(CAN_NOT_FIND_USER, name));
//    }

    public List<Ticket> getBookedTickets(final User user) {
        return tickets.stream()
                .filter(ticket -> user.getUserId() == ticket.getUser().getUserId() )
                .collect(Collectors.toList());
    }

//    public List<Ticket> getBookedTickets(final User user) {
//        List<Ticket> reservedTicketListCart = new ArrayList<>();
//        if (users != null && tickets != null) {
//            for (Ticket ticket : tickets) {
//                if (user.getUserId() == ticket.getUser().getUserId()) {
//                    reservedTicketListCart.add(ticket);
//                }
//            }
//        }
//        return reservedTicketListCart;
//    }

    @Deprecated
    public void showAllUsers() {
        users.forEach(user -> appLoger.logEvent(user.toString()));
        appLoger.logEvent("\n");
    }

    public boolean checkIfMailValid(final String email) {
        final Matcher matcher = EMAIL_REGEX_PATTERN.matcher(email);
        return matcher.find();
    }

    public boolean checkIfUserExists(final String name) {
//        return users.stream()
//                .anyMatch(user -> userMail.equals(user.getUserMail()));
        return users.stream()
                .anyMatch(user -> name.equals(user.getUserName()));
    }

    /**
     * This method adds default Production users to List<User> from usersData.xml {@see usersData.xml} <br>
     */
    public void addInitialUsers() {
        testUsers.forEach(userDataRow -> users.add(new User( //
                Integer.parseInt(userDataRow.get(0)), //
                userDataRow.get(1), //
                userDataRow.get(2), //
                userDataRow.get(3))));
    }



}
