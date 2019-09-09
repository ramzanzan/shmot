package labrat.shmot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bid {

    @Id
    @GeneratedValue
    private long Id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false, updatable = false)
    private Item TargetItem;

    @ManyToMany(mappedBy = "OutgoingBids")
    private Set<Item> OfferedItems = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false, updatable = false)
    private Person Bider;

    /**
     * Used abbreviations :
     * Bi - Bider
     * Ow - Owner
     * Cf - Confirmed
     * Dn - Deny
     * Wt - are being Waiting
     * To - Timeout
     */
    public enum State{
        Open,
        Paused,
        Refused,
        Closed,
        BeingExchanging,
        BiCoOwWt,//
        BiCoOwCo,
        BiCoOwDn,
        BiCoOwTo,
        BiDnOwWt,//
        BiDnOwCo,
        BiDnOwDn,
        BiDnOwTo,
        BiWtOwCo,//
        BiToOwCo,
        BiWtOwDn,
        BiToOwDn,
        BiToOwTo,//
    }

    @Enumerated(EnumType.STRING)
    private State Status = State.Open;

    public Bid(){}

    //region Properties
    public long getId() {
        return Id;
    }
    public Item getTargetItem() {
        return TargetItem;
    }

    public void setTargetItem(Item targetItem) {
        TargetItem = targetItem;
    }

    public Set<Item> getOfferedItems() {
        return OfferedItems;
    }
    public Person getBider() {
        return Bider;
    }

    public void setBider(Person bider) {
        Bider = bider;
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
        if(!(obj instanceof Bid)) return false;
        Bid b = (Bid)obj;
        return getBider().equals(b.getBider())
                && getStatus().equals(b.getStatus())
                && getTargetItem().equals(b.getTargetItem())
                && getOfferedItems().equals(b.getOfferedItems());
    }

    @Override
    public int hashCode() {
        return getBider().hashCode() ^ getTargetItem().hashCode();
    }
}
