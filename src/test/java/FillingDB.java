import labrat.shmot.model.Person;
import javax.persistence.EntityManager;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

public class FillingDB {

    public Person Al = new Person("Al");
    public Person Bo = new Person("Bo");
    public Person Ca = new Person("Ca");

    public FillingDB(SettingEnvironment se) throws SystemException, NotSupportedException {
        EntityManager em = se.EMF.createEntityManager();
        se.UTM.begin();
    }
}
