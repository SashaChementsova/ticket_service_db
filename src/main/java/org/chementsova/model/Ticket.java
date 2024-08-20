package org.chementsova.model;

import lombok.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Ticket {
    private int id;

    private TicketType ticketType;

    private Date creationDate;

    private int personId;

    public Ticket(TicketType ticketType, String creationDate, int personId) {
        this.ticketType = ticketType;
        this.creationDate = Date.valueOf(creationDate);
        this.personId = personId;
    }
}
