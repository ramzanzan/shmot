package labrat.shmot.model;

import javax.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue
    int Id;
    String name = "lexa";
}
