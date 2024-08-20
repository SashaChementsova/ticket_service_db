package org.chementsova.dao.daoImpl;

import org.chementsova.dao.TicketDAO;
import org.chementsova.model.Ticket;
import org.chementsova.model.TicketType;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TicketDAOImpl implements TicketDAO {

    private static final String QUERY_ADD_TICKET = "INSERT INTO Ticket (ticket_type, creation_date, user_id) VALUES (?, ?, ?)";

    private static final String QUERY_UPDATE_TICKET = "UPDATE Ticket SET ticket_type = ? WHERE id = ?";

    private static final String QUERY_GET_TICKET_BY_ID = "SELECT * FROM Ticket WHERE id = ?";

    private static final String QUERY_GET_TICKET_BY_USER_ID = "SELECT * FROM Ticket WHERE user_id = ?";

    private static final String QUERY_GET_TICKETS = "SELECT * FROM Ticket";
    
    private DataSource dataSource;
    
    public TicketDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveTicket(Ticket ticket) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_ADD_TICKET)) {
            statement.setObject(1, ticket.getTicketType(), java.sql.Types.OTHER);
            statement.setDate(2, (Date) ticket.getCreationDate());
            statement.setInt(3, ticket.getPersonId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ticket> getTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_GET_TICKETS)) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                TicketType ticketType = TicketType.valueOf(resultSet.getString("ticket_type"));
                Date creationDate = resultSet.getDate("creation_date");
                int personId = resultSet.getInt("user_id");
                tickets.add(new Ticket(id,ticketType,creationDate,personId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    @Override
    public List<Ticket> getTicketByID(int ticketId) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_GET_TICKET_BY_ID)) {
            statement.setInt(1, ticketId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                TicketType ticketType = TicketType.valueOf(resultSet.getString("ticket_type"));
                Date creationDate = resultSet.getDate("creation_date");
                int personId = resultSet.getInt("user_id");
                tickets.add(new Ticket(id,ticketType,creationDate,personId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByUserID(int personId) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_GET_TICKET_BY_USER_ID)) {
            statement.setInt(1, personId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                TicketType ticketType = TicketType.valueOf(resultSet.getString("ticket_type"));
                Date creationDate = resultSet.getDate("creation_date");
                int personId1 = resultSet.getInt("user_id");
                tickets.add(new Ticket(id,ticketType,creationDate,personId1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    @Override
    public void updateTicketType(int id, TicketType newTicketType) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_TICKET)) {
            statement.setObject(1, newTicketType, java.sql.Types.OTHER);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
