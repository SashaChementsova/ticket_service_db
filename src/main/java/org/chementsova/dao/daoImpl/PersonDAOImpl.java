package org.chementsova.dao.daoImpl;

import org.chementsova.DbUtils;
import org.chementsova.dao.PersonDAO;
import org.chementsova.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {
    @Override
    public void savePerson(Person person){
        String query = "INSERT INTO Person (name, creation_date) VALUES (?, ?)";
        try (Connection connection = DbUtils.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, person.getName());
            statement.setDate(2, (Date) person.getCreationDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Person> getPeople(){
        List<Person> people = new ArrayList<>();
        String query = "SELECT * FROM Person";
        try (Connection connection = DbUtils.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Date creationDate = resultSet.getDate("creation_date");
                people.add(new Person(id, name, creationDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    @Override
    public List<Person> getPersonByID(int personId){
        List<Person> people = new ArrayList<>();
        String query = "SELECT * FROM Person WHERE id = ?";
        try (Connection connection = DbUtils.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, personId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Date creationDate = resultSet.getDate("creation_date");
                people.add(new Person(id, name, creationDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    @Override
    public void deletePerson(int personId){
        String queryDeleteTickets = "DELETE FROM Ticket WHERE user_id = ?";
        String queryDeletePerson = "DELETE FROM Person WHERE id = ?";
        try (Connection connection = DbUtils.connect();
             PreparedStatement statement1 = connection.prepareStatement(queryDeleteTickets);
             PreparedStatement statement2 = connection.prepareStatement(queryDeletePerson)) {
            statement1.setInt(1, personId);
            statement1.executeUpdate();
            statement2.setInt(1, personId);
            statement2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
