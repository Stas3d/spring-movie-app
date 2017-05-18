import com.epam.springapp.dataModel.Event;
import com.epam.springapp.dataModel.Rating;
import com.epam.springapp.dataModel.Ticket;
import com.epam.springapp.dataModel.User;
import com.epam.springapp.exception.NoUserException;
import com.epam.springapp.user.UserServiceManager;
import com.epam.springapp.user.UserServiceManagerImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.*;

/**
 * @author Stanislav_Kryzhanovs
 */

public class UserServiceManagerTests {

    private static final int GENERATED_USERS_LIST_SIZE = 8;
    private static final String EXISTING_NAME = "Vasya";
    private static final String NON_EXISTING_NAME = "xxx1";
    private static final String VALID_FIRST_MAIL = "first@mail.ru";
    private static final String VALID_SECOND_MAIL = "second@mail.ru";
    private static final String VALID_THIRD_MAIL = "third@mail.ru";
    private static final String VALID_REGISTERED_MAIL = "vasya@mail.ru";
    private static final String VALID_NON_REGISTERED_MAIL = "no@mail.com";
    private static final String NOT_VALID_MAIL = "!not#valid_mail";
    private static final String BIRTH_DATE = "4.10.1984";

    private UserServiceManager manager;

    /**
     * This generates list of users, needed for testing
     */
    @BeforeClass
    public static void createUsersData() {
        generateTestingUserData();
    }

    /**
     * This method generates new instance of UserServiceManager class, also before - method destroys all ticketList that
     * could be generated at previous scenario
     */
    @Before
    public void setUp() {
        manager = new UserServiceManagerImpl();
        UserServiceManagerImpl.setTickets(new ArrayList<>());
    }

    @Test
    public void findGeneratedUsersSize() {
        assertThat(manager.findAllUsers()).hasSize(GENERATED_USERS_LIST_SIZE);
    }

    @Test
    public void testGettingExistingUser() {
        assertThat(manager.getUserByName(EXISTING_NAME)).isNotNull();
    }

    @Test(expected = NoUserException.class)
    public void testGettingNotExistingUser() {
        manager.getUserByName(NON_EXISTING_NAME);
    }

    @Test(expected = NoUserException.class)
    public void testGettingUserByNameWithNullArgument() {
        manager.getUserByName(null);
    }

    @Test(expected = NoUserException.class)
    public void testGetUserByMailWithNullArgument() {
        manager.getUserByEmail(null);
    }

    @Test
    public void testGetUserByExistingEmail() {
        assertThat(manager.getUserByEmail(VALID_REGISTERED_MAIL)).isNotNull();
    }

    @Test(expected = NoUserException.class)
    public void testGetUserByNotExistingEmail() {
        manager.getUserByEmail(VALID_NON_REGISTERED_MAIL);
    }

    @Test
    public void testGetUserByExistingId() {
        manager.getUserById(1);
    }

    @Test(expected = NoUserException.class)
    public void testGetUserByNotExistingId() {
        manager.getUserById(0);
    }

    @Test
    public void testThatRemoveAndRegisteringUserDoNotChangeTheListSize() {
        manager.removeUser(5);
        assertThat(manager.findAllUsers()).hasSize(GENERATED_USERS_LIST_SIZE - 1);
        manager.registerUser("Vova", 10, "vova@mail.ru", "1.1.1981");
        assertThat(manager.findAllUsers()).hasSize(GENERATED_USERS_LIST_SIZE);
    }

    @Test
    public void testThatRegisterUserWithExistingIdDoNotChangeListSize() {
        manager.registerUser("first", 1, VALID_FIRST_MAIL, BIRTH_DATE);
        manager.registerUser("second", 2, VALID_SECOND_MAIL, BIRTH_DATE);
        manager.registerUser("third", 3, VALID_THIRD_MAIL, BIRTH_DATE);
        assertThat(manager.findAllUsers()).hasSize(GENERATED_USERS_LIST_SIZE);
    }

    @Test
    public void testExistingUser() {
        assertThat(manager.checkIfUserExists(EXISTING_NAME)).isTrue();
    }

    @Test
    public void testNotExistingUser() {
        assertThat(manager.checkIfUserExists(VALID_NON_REGISTERED_MAIL)).isFalse();
    }

    @Test
    public void testValidMail() {
        assertThat(manager.checkIfMailValid(VALID_REGISTERED_MAIL)).isTrue();
    }

    @Test
    public void testNonValidMail() {
        assertThat(manager.checkIfMailValid(NOT_VALID_MAIL)).isFalse();
    }

    @Test
    public void testBookedTicketsListIsEmptyByDefault() {
        assertThat(manager.getBookedTickets(testUser())).isEmpty();
    }

    @Test
    public void testEmptyBookedTicketsList() {
        assertThat(manager.getBookedTickets(testUser())).isEmpty();
    }

    @Test
    public void testTicketsForUserAfterProvidingBooking() {
        generateOneTicketForTestUser();
        assertThat(manager.getBookedTickets(testUser())).hasSize(1);
    }

    private static void generateOneTicketForTestUser() {
        UserServiceManagerImpl.getTickets().add(new Ticket(testUser(), testEvent()));
    }

    private static User testUser() {
        return new User(1, EXISTING_NAME, VALID_REGISTERED_MAIL, BIRTH_DATE);
    }

    private static Event testEvent() {
        return new Event("Party Hard", "20.12.2012", 20, Rating.LOW);
    }

    private static void generateTestingUserData() {
        List<User> testUserData = new ArrayList<>();
        testUserData.add(new User(1, "Vasya", "vasya@mail.ru", "4.10.1984"));
        testUserData.add(new User(2, "Pete", "pete@mail.ru", "21.05.2001"));
        testUserData.add(new User(3, "Tawa", "tawa@mail.ru", "24.03.1990"));
        testUserData.add(new User(4, "Sasha", "sasha@mail.ru", "14.01.1980"));
        testUserData.add(new User(5, "Zaya", "zaya@mail.ru", "4.01.1979"));
        testUserData.add(new User(6, "Milena", "milena@mail.ru", "14.01.1980"));
        testUserData.add(new User(7, "Milana", "milana@mail.ru", "14.01.1980"));
        testUserData.add(new User(8, "Mimimi", "mimimi@mail.ru", "14.01.1980"));
        UserServiceManagerImpl.setUserList(testUserData);
    }
}
