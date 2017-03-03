import com.epam.springapp.booking.BookingServiceManager;
import com.epam.springapp.booking.BookingServiceManagerImpl;
import com.epam.springapp.event.EventServiceManager;
import com.epam.springapp.event.EventServiceManagerImpl;
import com.epam.springapp.user.UserServiceManager;
import com.epam.springapp.user.UserServiceManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;



/**
 * Created by Stanislav_Kryzhanovs on 10/18/2016.
 */

@Service
public class RestServiceTests {

//    private static final String PARTY_HARD = "Party Hard";
//    private ApplicationContext applicationContext;
//    private UserServiceManager userService;
//    private EventServiceManager eventService;
//    private BookingServiceManager bookingService;
//
//    MockRestServiceServer server;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Before
//    public final void setUp() {
//        applicationContext = new ClassPathXmlApplicationContext("config.xml");
//        userService = applicationContext.getBean("userService", UserServiceManagerImpl.class);
//        eventService = applicationContext.getBean("eventService", EventServiceManagerImpl.class);
//        bookingService = applicationContext.getBean("bookingService", BookingServiceManagerImpl.class);
//
//
//        server = MockRestServiceServer.createServer(restTemplate);
//    }
//
//    @After
//    public final void tearDown() {
//        userService = null;
//        eventService = null;
//        bookingService = null;
//    }
//
//    @BeforeClass
//    public static void addUsersAndEventsTestData() {
//        final UserServiceManager usersData = (UserServiceManager) new ClassPathXmlApplicationContext("usersData.xml")
//                .getBean("usersData");
//        usersData.addInitialUsers();
//        final EventServiceManager eventsData = (EventServiceManager) new ClassPathXmlApplicationContext("eventsData.xml")
//                .getBean("eventsData");
//        eventsData.addInitialEvents();
//    }

//    @Test
//    public void testGetMessage() {
//        server.expect(requestTo("http://localhost:8080/movie/users/getAllUsers"))
//                .andExpect(method(HttpMethod.GET))
//                .andRespond(withSuccess("resultSuccess", MediaType.TEXT_PLAIN));
//
////        String result = simpleRestService.getMessage();
////
////        server.verify();
////        assertThat(result, allOf(containsString("SUCCESS"),
////                containsString("resultSuccess")));
//    }


}
