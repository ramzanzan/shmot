package labrat.shmot.model;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {

    @Id
    @GeneratedValue
    public long Id;

    @NotNull
    @Enumerated(EnumType.STRING)
    public ItemType Type = ItemType.A;

    @org.hibernate.annotations.Immutable
    @org.hibernate.annotations.Type(type="labrat.shmot.model.ItemSize")
    @AttributeOverrides({
            @AttributeOverride(name = "Type", column = @Column(name = "size_type", nullable = false)),
            @AttributeOverride(name = "AdditionSize", column = @Column(name = "size_add"))
    })
    public ItemSize Size;

    @Column(length = 255)
    public String Description;

    //todo
    @Column(length = 255)
    public String Composition;

    @NotNull
    @Convert(converter = PathConverter.class)
    @Column(name = "primary_image")
    public Path PrimaryImage;

    @ElementCollection
    @Convert(converter = PathConverter.class)
    public Set<Path> Images = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @org.hibernate.annotations.OnDelete(action = OnDeleteAction.CASCADE)
    public Person Owner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "offered_item_in_bid")
    public Set<Bid> OutgoingBids = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "TargetItem")
    public Set<Bid> IngoingBids = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "bid_id")
    public Bid ConfirmedBid;

    public enum State{
        Open,
        Closed,
        BeingExchanging,
        Sold,
    }

    @Enumerated(EnumType.STRING)
    public State Status = State.Open;

    public Item(){}

    public Item(String ds){Description=ds;}
}
