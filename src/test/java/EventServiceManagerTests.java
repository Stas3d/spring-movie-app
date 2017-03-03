import com.epam.springapp.booking.BookingServiceManager;
import com.epam.springapp.booking.BookingServiceManagerImpl;
import com.epam.springapp.dataModel.Event;
import com.epam.springapp.dataModel.Rating;
import com.epam.springapp.event.EventServiceManager;
import com.epam.springapp.event.EventServiceManagerImpl;
import com.epam.springapp.exception.NoEventException;
import com.epam.springapp.user.UserServiceManager;
import com.epam.springapp.user.UserServiceManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Stanislav_Kryzhanovs
 */

public class EventServiceManagerTests {

    private static final String NON_EXISTING_EVENT = "321";

    private EventServiceManager manager;

    /**
     * This method generates list of events
     */
    @BeforeClass
    public static void createUsersData() {
        generateTestingEventData();
    }

    @Before
    public void setUp() {
        manager = new EventServiceManagerImpl();
        EventServiceManagerImpl.setEvents(new ArrayList<>());
    }

    @Test
    public void testSizeOfAllEvents() {
        generateTestingEventData();
        assertThat(manager.getAllEvents()).hasSize(4);
    }

    @Test(expected = NoEventException.class)
    public void testNonExistingEvent() {
        manager.getEventByName(NON_EXISTING_EVENT);
    }

    @Test(expected = NoEventException.class)
    public void testNullEvent() {
        manager.getEventByName(null);
    }

    @Test
    public void testCreatingEvent() {
        assertThat(manager.getAllEvents()).hasSize(0);
        manager.createEvent("New", "20.12.2012", 30, Rating.HIGH);
        assertThat(manager.getAllEvents()).hasSize(1);
    }

    @Test
    public void testCreatingExistingEventWontChangeEventsSize() {
        manager.createEvent("New", "20.12.2012", 30, Rating.HIGH);
        manager.createEvent("New", "20.12.2012", 30, Rating.HIGH);
        assertThat(manager.getAllEvents()).hasSize(1);
    }

    @Test
    public void testCreatingNullEvent() {
        manager.createEvent(null, "20.12.2012", 30, Rating.HIGH);
        assertThat(manager.getAllEvents()).hasSize(0);
    }

    @Test
    public void testRemovingEvent() throws Exception {
        manager.createEvent("New", "20.12.2012", 30, Rating.HIGH);
        assertThat(manager.getAllEvents()).hasSize(1);
        manager.removeEvent("New");
        assertThat(manager.getAllEvents()).hasSize(0);
    }

    public static void generateTestingEventData() {
        List<Event> testEventDataList = new ArrayList<>();
        testEventDataList.add(new Event("Party", "20.12.2012", 20, Rating.LOW));
        testEventDataList.add(new Event("Party Hard", "20.12.2012", 20, Rating.LOW));
        testEventDataList.add(new Event("Standard Event", "20.12.2012", 20, Rating.LOW));
        testEventDataList.add(new Event("University Event", "20.12.2012", 20, Rating.LOW));
        EventServiceManagerImpl.setEvents(testEventDataList);
    }
}