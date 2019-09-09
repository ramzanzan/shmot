package environment;

import com.atomikos.icatch.jta.*;
import com.atomikos.jdbc.AtomikosDataSourceBean;

import javax.naming.*;
import javax.persistence.*;
import javax.transaction.*;
import javax.sql.*;
import java.util.Properties;

public class SettingEnvironment {

    private AtomikosDataSourceBean adsb;
    public TransactionManager ut;
    public DataSource ds;
    public EntityManagerFactory emf;

    public SettingEnvironment(String user, String pass, String dbName, String jndiName, String puName){
        adsb = new AtomikosDataSourceBean();
        adsb.setUniqueResourceName(jndiName);
        adsb.setXaDataSourceClassName("org.postgresql.xa.PGXADataSource");
        Properties p = new Properties();
        p.setProperty ( "user" , user );
        p.setProperty ( "password" , pass );
        p.setProperty ( "serverName" , "localhost" );
        p.setProperty ( "portNumber" , "5432" );
        p.setProperty ( "databaseName" , dbName );
        adsb.setXaProperties (p);
        adsb.setPoolSize(5);

        ut = new UserTransactionManager();
        ds = adsb;
        try {
            Context ctx = new InitialContext();
            ctx.bind(jndiName, ds);
        }catch (NamingException ne){
            throw new RuntimeException(ne);
        }
        emf = Persistence.createEntityManagerFactory( puName );
    }

    public SettingEnvironment(){
        this("labrat","p","shmot","shmotDS","shmotPU");
    }
}
