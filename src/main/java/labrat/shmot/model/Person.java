package labrat.shmot.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue
    private long Id;

    @OneToMany(mappedBy = "Owner", cascade = CascadeType.ALL)
    private Set<Item> Items = new HashSet<>();

    @OneToMany(mappedBy = "Bider", cascade = CascadeType.ALL)
    private Set<Bid> Bids = new HashSet<>();

    //todo something adequate
    @Column(unique = true)
    private String Name;

    public Person(){}

    public Person(String name){Name=name;}

    //region Properties
    public long getId() {
        return Id;
    }
    public Set<Item> getItems() {
        return Items;
    }
    public Set<Bid> getBids() {
        return Bids;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    //endregion


    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(!(obj instanceof Person)) return false;
        return getName().equals(((Person)obj).getName());
//        return Name.equals(((Person)obj).Name;
        //todo try with persist references
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
