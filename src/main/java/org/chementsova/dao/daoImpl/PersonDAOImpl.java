package org.chementsova.dao.daoImpl;

import org.chementsova.dao.PersonDAO;
import org.chementsova.model.Person;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAOImpl implements PersonDAO {

    private static final String QUERY_ADD_PERSON = "INSERT INTO Person (name, creation_date) VALUES (?, ?)";

    private static final String QUERY_GET_PEOPLE = "SELECT * FROM Person";

    private static final String QUERY_REMOVE_PERSON = "DELETE FROM Person WHERE id = ?";

    private static final String QUERY_GET_PERSON_BY_ID = "SELECT * FROM Person WHERE id = ?";

    private DataSource dataSource;
    
    public PersonDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void savePerson(Person person) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_ADD_PERSON)) {
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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_GET_PEOPLE)) {
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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_GET_PERSON_BY_ID)) {
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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement2 = connection.prepareStatement(QUERY_REMOVE_PERSON)) {
            statement2.setInt(1, personId);
            statement2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
