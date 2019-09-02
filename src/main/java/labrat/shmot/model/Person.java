package labrat.shmot.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue
    public int Id;

    @OneToMany(mappedBy = "Owner", cascade = CascadeType.ALL)
    public Set<Item> Items = new HashSet<>();

    @OneToMany(mappedBy = "Bider", cascade = CascadeType.ALL)
    public Set<Bid> Bids = new HashSet<>();

    public String Name;

    public Person(){}

    public Person(String name){Name=name;}
}
