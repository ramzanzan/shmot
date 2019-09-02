package labrat.shmot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bid {

    @Id
    @GeneratedValue
    public long Id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "item_id", updatable = false)
    public Item TargetItem;

    @ManyToMany(mappedBy = "OutgoingBids")
    public Set<Item> OfferedItems = new HashSet<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "person_id",updatable = false)
    public Person Bider;

    public enum State{
        Open,
        Closed,
        BeingExchanging,
        WaitingToConfirm,
        Confirmed,
        Refused,
    }

    @Enumerated(EnumType.STRING)
    public State Status = State.Open;

    public Bid(){}
}
