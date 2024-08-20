package org.chementsova.dao;

import org.chementsova.model.Ticket;
import org.chementsova.model.TicketType;

import java.util.List;

public interface TicketDAO {
    void saveTicket(Ticket ticket);

    List<Ticket> getTickets();

    List<Ticket> getTicketByID(int ticketId);

    List<Ticket> getTicketsByUserID(int personId);

    void updateTicketType(int id, TicketType newTicketType);
}
