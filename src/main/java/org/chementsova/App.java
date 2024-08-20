package org.chementsova;

import org.chementsova.dao.*;
import org.chementsova.dao.daoImpl.*;
import org.chementsova.model.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.*;
import java.util.List;


public class App 
{
    public static void main( String[] args ) throws SQLException {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(DataConfig.class);

        TicketDAO ticketDAO = ctx.getBean(TicketDAOImpl.class);

        PersonDAO personDAO = ctx.getBean(PersonDAOImpl.class);

        List<Ticket> tickets = ticketDAO.getTickets();
        printTickets(tickets);

        List<Person> people = personDAO.getPeople();
        printPeople(people);

//        personDAO.savePerson(new Person("Masha","2024-08-12"));
//
//        ticketDAO.saveTicket(new Ticket(TicketType.BUS,"2024-08-11",1));
//
//        ticketDAO.updateTicketType(1, TicketType.BUS);
//
//        tickets = ticketDAO.getTickets();
//        printTickets(tickets);
//
//        personDAO.deletePerson(1);
//
//        tickets = ticketDAO.getTickets();
//        printTickets(tickets);
//
//        people = personDAO.getPeople();
//        printPeople(people);
//
//        people = personDAO.getPersonByID(2);
//        printPeople(people);
//
//        tickets = ticketDAO.getTicketsByUserID(2);
//        printTickets(tickets);
//
//        tickets = ticketDAO.getTicketByID(3);
//        printTickets(tickets);
//
//    }
    }
    private static void printTickets(List<Ticket> tickets){
        if (tickets.isEmpty()) {
            System.out.println("Tickets are empty");
        } else {
            for(Ticket ticket : tickets){
                System.out.println(ticket.toString());
            }
        }
    }

    private static void printPeople(List<Person> people){
        if (people.isEmpty()) {
            System.out.println("People are empty");
        } else {
            for(Person person : people){
                System.out.println(person.toString());
            }
        }
    }
}
