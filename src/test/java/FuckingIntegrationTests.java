import com.epam.springapp.aspect.CounterAspect;
import com.epam.springapp.booking.BookingServiceManager;
import com.epam.springapp.booking.BookingServiceManagerImpl;
import com.epam.springapp.dataModel.Auditorium;
import com.epam.springapp.dataModel.Event;
import com.epam.springapp.dataModel.Ticket;
import com.epam.springapp.event.EventServiceManager;
import com.epam.springapp.event.EventServiceManagerImpl;
import com.epam.springapp.exception.NoEventException;
import com.epam.springapp.exception.NoUserException;
import com.epam.springapp.user.UserServiceManager;
import com.epam.springapp.user.UserServiceManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Stanislav_Kryzhanovs
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestExecutionListeners({ DirtiesContextTestExecutionListener.class })
public class FuckingIntegrationTests {

    private static final String PARTY_HARD = "Party Hard";
    private ApplicationContext applicationContext;
    private UserServiceManager userService;
    private EventServiceManager eventService;
    private BookingServiceManager bookingService;

    @Before
    public final void setUp() {
        applicationContext = new ClassPathXmlApplicationContext("config.xml");
        userService = applicationContext.getBean("userService", UserServiceManagerImpl.class);
        eventService = applicationContext.getBean("eventService", EventServiceManagerImpl.class);
        bookingService = applicationContext.getBean("bookingService", BookingServiceManagerImpl.class);
    }

    @After
    public final void tearDown() {
        userService = null;
        eventService = null;
        bookingService = null;
    }

    @BeforeClass
    public static void addUsersAndEventsTestData() {
        final UserServiceManager usersData = (UserServiceManager) new ClassPathXmlApplicationContext("usersData.xml")
                .getBean("usersData");
        usersData.addInitialUsers();
        final EventServiceManager eventsData = (EventServiceManager) new ClassPathXmlApplicationContext("eventsData.xml")
                .getBean("eventsData");
        eventsData.addInitialEvents();
    }

    @Test
    public void testThatUsersSizeReducesAfterRemovingOneOfTheUser() {
        assertThat(userService.findAllUsers()).hasSize(8);
        userService.removeUser(1);
        assertThat(userService.findAllUsers()).hasSize(7);
    }

    @Test
    public void testThatEventSizeReducesAfterRemovingOneOfTheEvent() throws Exception {
        eventService.assignAuditoriumForEvent(eventService.getEventByName(PARTY_HARD), Auditorium.A101);
        assertThat(eventService.getAllEvents()).hasSize(4);
        eventService.removeEvent("Party");
        assertThat(eventService.getAllEvents()).hasSize(3);
    }

    @Test(expected = NoEventException.class)
    public void testRemovingNonExistingEvent() throws Exception {
        eventService.assignAuditoriumForEvent(eventService.getEventByName(PARTY_HARD), Auditorium.A101);
        eventService.removeEvent("NonExisting");
    }

    @Test
    public void testThatOnlyOneTicketWasBookedForEvent() {
        final Event simpleEvent = eventService.getEventByName(PARTY_HARD);
        bookingService.bookTicket(userService.getUserById(1), simpleEvent);
        final List<Ticket> list = bookingService.getTicketsForEvent(simpleEvent, null);
        assertThat(list).hasSize(1);
    }

    @Test
    public void testThatCounterAspectMethodWasCalledOnlyOneTime() {
        final CounterAspect counterAspect = applicationContext.getBean("aspectBean", CounterAspect.class);
        final Event simpleEvent = eventService.getEventByName(PARTY_HARD);
        bookingService.bookTicket(userService.getUserById(1), simpleEvent);
        assertThat(counterAspect.getCountBookTicketMethodCalled()).isEqualTo(1);
    }
}
