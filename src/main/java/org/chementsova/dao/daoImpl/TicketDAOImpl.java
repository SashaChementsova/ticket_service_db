package org.chementsova.dao.daoImpl;

import org.chementsova.DbUtils;
import org.chementsova.dao.TicketDAO;
import org.chementsova.model.Ticket;
import org.chementsova.model.TicketType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    @Override
    public void saveTicket(Ticket ticket){
        String query = "INSERT INTO Ticket (ticket_type, creation_date, user_id) VALUES (?, ?, ?)";
        try (Connection connection = DbUtils.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ticket.getTicketType().toString());
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
        String query = "SELECT * FROM Ticket";
        try (Connection connection = DbUtils.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
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
    public List<Ticket> getTicketByID(int ticketId){
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM Ticket WHERE id = ?";
        try (Connection connection = DbUtils.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
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
    public List<Ticket> getTicketsByUserID(int personId){
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM Ticket where user_id = ?";
        try (Connection connection = DbUtils.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
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
    public void updateTicketType(int id, TicketType newTicketType){
        String query = "UPDATE Ticket SET ticket_type = ? WHERE id = ?";
        try (Connection connection = DbUtils.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newTicketType.toString());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
