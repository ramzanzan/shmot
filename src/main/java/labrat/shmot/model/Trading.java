package labrat.shmot.model;

import javax.transaction.Transactional;

public class Trading {

//    @Transactional(value = Tx)
    //todo
    public static void MakeBid(Person bider, Item target, Item... offered){
        if(target.Owner.equals(bider)) throw new IllegalArgumentException("Bider are Owner");
        if(offered.length==0) throw new IllegalArgumentException("Zero bid");
        if(target.Status!=Item.State.Open || target.Status!= Item.State.BeingExchanging)
            throw new IllegalStateException("Target.Status: "+target.Status);
        for(Item i : offered){
            if(i.Status!=Item.State.Open) throw new IllegalStateException("Offered item isn't Open");
            if(bider.equals(i.Owner)) throw new IllegalArgumentException("Bider doesn't own offered item "+i.toString());
        }
    }

}
