package control;

import java.util.List;
import org.hibernate.Session;

public interface DAOService<E> {

    Session openSession();

    List<E> getAllData();

    boolean insertData(E param);

    boolean updateData(E param);

    boolean deleteData(E param);

    E getData(Object pk);
}
