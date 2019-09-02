import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import labrat.shmot.model.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestTest {

    static SettingEnvironment env = new SettingEnvironment();

    @Test
    void T1(){
        try {
            EntityManager em = env.EMF.createEntityManager();
            UserTransaction ut = env.UTM;
            ut.begin();
            em.persist(new Person());
            em.persist(new Person());
            ut.commit();
            em.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
