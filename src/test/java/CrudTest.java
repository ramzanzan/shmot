import environment.FilledDB;
import environment.SettingEnvironment;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;

import labrat.shmot.model.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CrudTest {

    SettingEnvironment env;
    FilledDB db;

    @BeforeAll
    void Setting() {
        try {
            env = new SettingEnvironment();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        db = new FilledDB(env);
    }

    @BeforeEach
    void Filling(){
        db.FillDb();
    }

    void Template(){
        try {
            EntityManager em = env.emf.createEntityManager();
            env.ut.begin();

            env.ut.commit();
            em.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Disabled
    void RemoveAndPersist() {
        try {
            PersistenceUnitUtil puu = env.emf.getPersistenceUnitUtil();
            env.ut.begin();
            EntityManager em = env.emf.createEntityManager();

            Person a = em.find(Person.class, db.A.getId());
            em.remove(a);
            env.ut.commit();
            em.close();


            em = env.emf.createEntityManager();
            env.ut.begin();
            em.merge(a);
            env.ut.commit();
            em.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void DeletionCase1(){
        try {
            env.ut.begin();
            EntityManager em = env.emf.createEntityManager();

            Person a = em.find(Person.class,db.A.getId());
            Person b = em.find(Person.class,db.B.getId());
            em.remove(a);
            em.remove(b);
            Long c = (Long) em.createQuery("select count(i) from Item i").getSingleResult();
            Assertions.assertEquals(2,c);

            env.ut.commit();
            em.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void DeletionCase2(){
        try {
            env.ut.begin();
            EntityManager em = env.emf.createEntityManager();

            Person a = em.find(Person.class,db.A.getId());
            Person b = em.find(Person.class,db.B.getId());
            em.remove(a);
            em.remove(b);
            Long c = (Long) em.createQuery("select count(i) from Item i").getSingleResult();
            Assertions.assertEquals(2,c);

            env.ut.commit();
            em.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}

