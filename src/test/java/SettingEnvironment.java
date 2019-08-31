
import com.atomikos.icatch.jta.*;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import labrat.shmot.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.*;

import javax.naming.*;
import javax.naming.spi.InitialContextFactory;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.*;
import javax.sql.*;
import java.util.Hashtable;
import java.util.Properties;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SettingEnvironment {

    private static UserTransactionManager utm;
    private static AtomikosDataSourceBean adsb;

    // Standard interfaces
    private static TransactionManager tm;
    private static DataSource ds;

    @Test
    public void test(){
        adsb = new AtomikosDataSourceBean();
        adsb.setUniqueResourceName("shmotDS");
        adsb.setXaDataSourceClassName("org.postgresql.xa.PGXADataSource");
        Properties p = new Properties();
        p.setProperty ( "user" , "labrat" );
        p.setProperty ( "password" , "p" );
        p.setProperty ( "serverName" , "localhost" );
        p.setProperty ( "portNumber" , "5432" );
        p.setProperty ( "databaseName" , "shmot" );
        adsb.setXaProperties ( p );
        adsb.setPoolSize(5);

        utm = new UserTransactionManager();
        tm=utm;
        ds=adsb;

        try {

            Context ctx = new InitialContext();
            ctx.bind("shmotDS",ds);
            ctx.l
        }catch (Exception e){e.printStackTrace();}


        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "shmotPU" );
        EntityManager em = emf.createEntityManager();
//        EntityManager em = sf.createEntityManager();
        try {
            tm.begin();
            em.persist(new User());
            tm.commit();
            em.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
