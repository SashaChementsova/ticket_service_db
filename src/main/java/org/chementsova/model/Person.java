package org.chementsova.model;

import lombok.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Person {
    int id;

    String name;

    Date creationDate;

    public Person(String name, String date){
        this.name = name;
        this.creationDate = Date.valueOf(date);
    }
}
