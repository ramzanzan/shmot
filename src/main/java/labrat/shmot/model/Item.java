package labrat.shmot.model;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private long Id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemType Type = ItemType.A;

    @org.hibernate.annotations.Immutable
    @org.hibernate.annotations.Type(type="labrat.shmot.model.ItemSize")
    @AttributeOverrides({
            @AttributeOverride(name = "Type", column = @Column(name = "size_type", nullable = false)),
            @AttributeOverride(name = "AdditionSize", column = @Column(name = "size_add"))
    })
    private ItemSize Size = new ItemSize();

    @Column(length = 255)
    private String Description;

    //todo
    @Column(length = 255)
    private String Composition;

    @Convert(converter = PathConverter.class)
    @Column(name = "primary_image", nullable = false)
    private Path PrimaryImage;

    @ElementCollection
    @Convert(converter = PathConverter.class)
    private Set<Path> Images = new HashSet<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @org.hibernate.annotations.OnDelete(action = OnDeleteAction.CASCADE)
    private Person Owner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "item_part_of_bid")
    private Set<Bid> OutgoingBids = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "TargetItem")
    private Set<Bid> IngoingBids = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "bid_id")
    private Bid ConfirmedBid;

    public enum State{
        Open,
        Paused,
        Closed,
        BeingExchanging,
        Exchanged,
    }

    @Enumerated(EnumType.STRING)
    private State Status = State.Open;

    public Item(){}

    public Item(Person owner){
        setOwner(owner);
    }

    public Item(Person owner, Path primeImage){
        setOwner(owner);
        PrimaryImage=primeImage;
    }

    //region Properties
    public long getId() {
        return Id;
    }
    public ItemType getType() {
        return Type;
    }

    public void setType(ItemType type) {
        Type = type;
    }

    public ItemSize getSize() {
        return Size;
    }

    public void setSize(ItemSize size) {
        Size = size;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getComposition() {
        return Composition;
    }

    public void setComposition(String composition) {
        Composition = composition;
    }

    public Path getPrimaryImage() {
        return PrimaryImage;
    }

    public void setPrimaryImage(Path primaryImage) {
        PrimaryImage = primaryImage;
        //todo ?
    }

    public Set<Path> getImages() {
        return Images;
    }
    public Person getOwner() {
        return Owner;
    }
    public void setOwner(Person owner) {
        Owner = owner;
    }

    public Set<Bid> getOutgoingBids() {
        return OutgoingBids;
    }
    public Set<Bid> getIngoingBids() {
        return IngoingBids;
    }
    public Bid getConfirmedBid() {
        return ConfirmedBid;
    }

    public void setConfirmedBid(Bid confirmedBid) {
        ConfirmedBid = confirmedBid;
    }

    public State getStatus() {
        return Status;
    }

    public void setStatus(State status) {
        Status = status;
    }
    //endregion

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(!(obj instanceof Item)) return false;
        Item i = (Item)obj;
        return getOwner().equals(i.getOwner()) && getPrimaryImage().equals(i.getPrimaryImage());
    }

    @Override
    public int hashCode() {
        return getPrimaryImage().hashCode();
    }
}
