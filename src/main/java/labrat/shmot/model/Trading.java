package labrat.shmot.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

public class Trading {

    public static UserTransaction ut;
    public static EntityManagerFactory efm;

    //todo
    @Transactional
    public static void MakeBid(Person bider, Item target, Item... offered){
//        if(target.getOwner().equals(bider)) throw new IllegalArgumentException("Bider are Owner");
//        if(offered.length==0) throw new IllegalArgumentException("Zero bid");
//        if(target.Status!=Item.State.Open || target.Status!= Item.State.BeingExchanging)
//            throw new IllegalStateException("Target.Status: "+target.Status);
//        for(Item i : offered){
//            if(i.Status!=Item.State.Open) throw new IllegalStateException("Offered item isn't Open");
//            if(bider.equals(i.Owner)) throw new IllegalArgumentException("Bider doesn't own offered item "+i.toString());
//        }
    }

    @Transactional
    public void TrTest(long personId){
        EntityManager em = efm.createEntityManager();
        Person p = em.find(Person.class,personId);
        p.setName("lel");
        em.
    }

}
