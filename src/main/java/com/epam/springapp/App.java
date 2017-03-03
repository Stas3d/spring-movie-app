package com.epam.springapp;

import com.epam.springapp.aspect.CounterAspect;
import com.epam.springapp.aspect.DiscountAspect;
import com.epam.springapp.auditorium.AuditoriumService;
import com.epam.springapp.auditorium.AuditoriumServiceImpl;
import com.epam.springapp.booking.BookingServiceManager;
import com.epam.springapp.booking.BookingServiceManagerImpl;
import com.epam.springapp.dataModel.Auditorium;
import com.epam.springapp.event.EventServiceManager;
import com.epam.springapp.event.EventServiceManagerImpl;
import com.epam.springapp.user.UserServiceManager;
import com.epam.springapp.user.UserServiceManagerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@Configuration
@ComponentScan({ "com.epam.springapp" })
//@Import({ AppSecurityConfig.class })

public class App {

    /**
     * This was made to run the application with MVC addon
     */
    static {

        try {
//            new App().run();
            main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Main class to be able to run it directly as a java program.
     */
    public static void main(String[] args) throws Exception {
        new App().run();
    }

    private void run() throws Exception{
        addInitialProdUsersToApplication();
        addInitialProdEventsToApplication();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");
        // DataBaseHepler.initDataBase(applicationContext);

        UserServiceManager userService = applicationContext.getBean("userService", UserServiceManagerImpl.class);
        userService.removeUser(6);
        userService.showAllUsers();

        EventServiceManager eventService = applicationContext.getBean("eventService", EventServiceManagerImpl.class);
        eventService.assignAuditoriumForEvent(eventService.getEventByName("Party Hard"), Auditorium.A101);
        eventService.removeEvent("Party");
        eventService.printAllEvents();

        BookingServiceManager bookingService = applicationContext.getBean("bookingService", BookingServiceManagerImpl.class);
        bookingService.bookTicket(userService.getUserById(1), eventService.getEventByName("Party Hard"));
        bookingService.bookTicket(userService.getUserById(2), eventService.getEventByName("Standard Event"));
        bookingService.printAllTickets();

        AuditoriumService auditoriumService = applicationContext.getBean("auditorium", AuditoriumServiceImpl.class);
        auditoriumService.assignAuditorium(eventService.getEventByName("Party Hard"), Auditorium.A100);

        discountAndCountActivities(applicationContext);
    }
    
    private static void addInitialProdUsersToApplication() {
        final ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("usersData.xml");
        final UserServiceManager usersDataContext = (UserServiceManager) classPathXmlApplicationContext.getBean("usersData");
        usersDataContext.addInitialUsers();
    }

    private static void addInitialProdEventsToApplication() {
        final ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("eventsData.xml");
        final EventServiceManager eventsDataContext = (EventServiceManager) classPathXmlApplicationContext.getBean("eventsData");
        eventsDataContext.addInitialEvents();
    }

    private static void discountAndCountActivities(final ApplicationContext applicationContext) {
        final CounterAspect counterAspect = applicationContext.getBean("aspectBean", CounterAspect.class);
        counterAspect.showInvocationsNumberForEachEvent();
        counterAspect.getCountBookTicketMethodCalled();
        counterAspect.showBookTicketCountForAllUsers();

        final DiscountAspect discountAspect = applicationContext.getBean("discountBean", DiscountAspect.class);
        discountAspect.getBirthdayDiscountStrategyCount();
        discountAspect.getEvery10thTicketStrategyCount();
    }

//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver
//                = new InternalResourceViewResolver();
//        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/WEB-INF/views/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }
}