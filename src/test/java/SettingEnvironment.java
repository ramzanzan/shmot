import com.atomikos.icatch.jta.*;
import com.atomikos.jdbc.AtomikosDataSourceBean;

import javax.naming.*;
import javax.persistence.*;
import javax.transaction.*;
import javax.sql.*;
import java.util.Properties;

public class SettingEnvironment {

    public UserTransactionManager UTM;
    public AtomikosDataSourceBean ADSB;
    public TransactionManager TM;
    public DataSource DS;
    public EntityManagerFactory EMF;

    public SettingEnvironment(String user, String pass, String dbName, String jndiName, String puName){
        ADSB = new AtomikosDataSourceBean();
        ADSB.setUniqueResourceName(jndiName);
        ADSB.setXaDataSourceClassName("org.postgresql.xa.PGXADataSource");
        Properties p = new Properties();
        p.setProperty ( "user" , user );
        p.setProperty ( "password" , pass );
        p.setProperty ( "serverName" , "localhost" );
        p.setProperty ( "portNumber" , "5432" );
        p.setProperty ( "databaseName" , dbName );
        ADSB.setXaProperties (p);
        ADSB.setPoolSize(5);

        UTM = new UserTransactionManager();
        TM = UTM;
        DS = ADSB;
        try {
            Context ctx = new InitialContext();
            ctx.bind(jndiName, DS);
        }catch (NamingException ne){
            throw new RuntimeException(ne);
        }
        EMF = Persistence.createEntityManagerFactory( puName );
    }

    public SettingEnvironment(){
        this("labrat","p","shmot","shmotDS","shmotPU");
    }
}
