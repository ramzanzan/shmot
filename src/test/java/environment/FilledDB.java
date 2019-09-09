package environment;

import labrat.shmot.model.Item;
import labrat.shmot.model.Person;
import mocks.MockPath;

import javax.persistence.EntityManager;

public class FilledDB {

    private SettingEnvironment se;
    public Person A,B,C;
    public Item A1, A2, B1, B2, C1, C2;

    public FilledDB(SettingEnvironment se){
        this.se =se;
    }

    public void FillDb(){
        A = new Person("A");
        B = new Person("B");
        C = new Person("C");
        A1 = new Item(A, new MockPath("A1"));
        A2 = new Item(A, new MockPath("A2"));
        A.getItems().add(A1);
        A.getItems().add(A2);
        B1 = new Item(B, new MockPath("B1"));
        B2 = new Item(B, new MockPath("B2"));
        B.getItems().add(B1);
        B.getItems().add(B2);
        C1 = new Item(C, new MockPath("C1"));
        C2 = new Item(C, new MockPath("C2"));
        C.getItems().add(C1);
        C.getItems().add(C2);
        try {
            se.ut.begin();
            EntityManager em = se.emf.createEntityManager();
            em.createQuery("delete from Item").executeUpdate();
            em.createQuery("delete from Person").executeUpdate();
            em.persist(A);
            em.persist(B);
            em.persist(C);
            se.ut.commit();
            em.close();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
