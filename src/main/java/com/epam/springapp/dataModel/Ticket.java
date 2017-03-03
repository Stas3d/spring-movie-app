package com.epam.springapp.dataModel;

import com.epam.springapp.booking.BookingServiceManagerImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Random;


@RequiredArgsConstructor(staticName = "of")

@Entity
@Table(name = "TICKET")
@ToString(includeFieldNames = true)
public class Ticket {

    @Id
    @Column(name = "TICKET_ID", nullable = false)
    @Getter
    private int ticketId;

    @Column(name = "USERNAME", nullable = false, length = 255, unique = false)
    @Getter
    private User user;

    @Column(name = "EVENT", nullable = false)
    @Getter
    private Event event;

    /**
     * This constructor invokes generating random number so was not replaced by lombok
     */
    public Ticket(final User user, final Event event) {
        this.ticketId = generateRandomNumber();
        this.user = user;
        this.event = event;
    }
    // TODO should be redone

    private int generateRandomNumber() {
        final int i = new Random().nextInt(100_000);
        boolean existingValue = BookingServiceManagerImpl.getTicketList()
                .stream()
                .anyMatch(t ->
                        t.getTicketId() == i);

        return existingValue ? generateRandomNumber() : i;
    }
}
