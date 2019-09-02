import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import labrat.shmot.model.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CrudTest {

    static SettingEnvironment env;

    static {
        try {
            env = new SettingEnvironment();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Test
    void Раб(){
        try {
            EntityManager em = env.EMF.createEntityManager();
            env.UTM.begin();
            Person p1 = new Person();
            em.persist(p1);
            em.persist(new Person());
            em.persist(new Person());
            env.UTM.commit();
            p1.Name="abc";
            env.UTM.begin();
            em.merge(p1);
            env.UTM.commit();
            em.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
