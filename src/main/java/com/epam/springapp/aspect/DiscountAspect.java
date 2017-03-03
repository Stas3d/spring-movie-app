package com.epam.springapp.aspect;

/**
 * @author Stanislav_Kryzhanovs
 */

import com.epam.springapp.apploger.AppLog;
import com.epam.springapp.dataModel.User;
import lombok.Setter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.HashMap;

/**
 * count how many times each discount
 * was given total and for specific user
 */

@Aspect
public class DiscountAspect {
    private static final String BIRTHDAY_DISCOUNT_STRATEGY_LOGIC_COUNT = "Birthday Discount Strategy Logic was invoked [%s] times";
    private static final String TICKET_DISCOUNT_STRATEGY_LOGIC_COUNT = "Every 10th Ticket Discount Strategy Logic was invoked [%s] times";
    private static final String INVOCATIONS_FOR_BIRTHDAY_DISCOUNT = "For user [%s] there was [%s] invocations for Birthday Discount";
    private static final String INVOCATIONS_FOR_10TH_TICKET_DISCOUNT = "For user [%s] there was [%s] invocations for 10th Ticket Discount";
    private int totalBirthdayDiscountCount;
    private int countEvery10thTicketDiscountCount;
    private HashMap<String, Integer> birthdayDiscountInvocations = new HashMap<>();
    private HashMap<String, Integer> every10thTicketInvocations = new HashMap<>();

    @Autowired
    @Setter
    private AppLog appLoger;

    @Autowired
    private JdbcOperations jdbcOperations;

    @Before("execution(* com.epam.springapp.discount.DiscountService.getBirthdayDiscountStrategyCount(..))")
    public void birthdayDiscountStrategy(final JoinPoint joinPoint) {
        final Object[] signatureArgs = joinPoint.getArgs();
        final User user = (User) signatureArgs[0];
        final String userName = user.getUserName();
        if (!birthdayDiscountInvocations.containsKey(userName)) {
            birthdayDiscountInvocations.put(userName, 0);
        }
        birthdayDiscountInvocations.put(userName, (birthdayDiscountInvocations.get(userName) + 1));
        totalBirthdayDiscountCount++;
//        int result = jdbcOperations.update("INSERT into BIRTHDAYDISCOUNTINVOCATIONS(USERNAME,COUNT) VALUES (?,?)",
//                userName, (birthdayDiscountInvocations.get(userName) + 1));
    }

    public int getBirthdayDiscountStrategyCount() {
        birthdayDiscountInvocations
                .keySet()
                .stream()
                .forEach(key ->
                        String.format(INVOCATIONS_FOR_BIRTHDAY_DISCOUNT, key, birthdayDiscountInvocations.get(key)));
        appLoger.logEvent(String.format(BIRTHDAY_DISCOUNT_STRATEGY_LOGIC_COUNT, totalBirthdayDiscountCount));
        return totalBirthdayDiscountCount;
    }

    @Before("execution(* com.epam.springapp.discount.DiscountService.getEvery10thTicketDiscount(..))")
    public void every10thTicketDiscount(final JoinPoint joinPoint) {
        final Object[] signatureArgs = joinPoint.getArgs();
        final User user = (User) signatureArgs[0];
        final String userName = user.getUserName();
        if (!every10thTicketInvocations.containsKey(userName)) {
            every10thTicketInvocations.put(userName, 0);
        }
        every10thTicketInvocations.put(userName, (every10thTicketInvocations.get(userName) + 1));
        countEvery10thTicketDiscountCount++;
//        int result = jdbcOperations.update("INSERT into EVERYTENTHTICKETDISCOUNTINVOCATIONS(USERNAME,COUNT) VALUES (?,?)",
//                userName, (every10thTicketInvocations.get(userName) + 1));
    }

    public int getEvery10thTicketStrategyCount() {
        every10thTicketInvocations
                .keySet()
                .stream()
                .forEach(key ->
                        String.format(INVOCATIONS_FOR_10TH_TICKET_DISCOUNT, key, every10thTicketInvocations.get(key)));
        appLoger.logEvent(String.format(TICKET_DISCOUNT_STRATEGY_LOGIC_COUNT, countEvery10thTicketDiscountCount));
        return countEvery10thTicketDiscountCount;
    }
}
