import com.epam.springapp.dataModel.Auditorium;
import com.epam.springapp.event.EventServiceManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AnotherTestsWithMockTests {

    private static final String PARTY_HARD = "Party Hard";

    @Mock
    private EventServiceManager eventService;

    @Mock
    private EventServiceManager manager;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void test_adding_initial_events_was_invoked_only_once() {
        manager.addInitialEvents();
        verify(manager, times(1)).addInitialEvents();
    }

    @Test
    public void test_assign_auditorium_for_events_was_invoked_only_once() {
        eventService.assignAuditoriumForEvent(eventService.getEventByName(PARTY_HARD), Auditorium.A101);
        verify(eventService, times(1)).assignAuditoriumForEvent(eventService.getEventByName(PARTY_HARD),
                Auditorium.A101);
    }
}
