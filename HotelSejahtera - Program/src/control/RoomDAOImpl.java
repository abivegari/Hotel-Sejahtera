/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.List;
import model.Room;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Developer
 */
public class RoomDAOImpl implements DAOService<Room>{
    
    private final SessionFactory session = HibernateUtil.getSessionFactory();

    @Override
    public Session openSession() {
        return session.openSession();
    }

    @Override
    public List<Room> getAllData() {
        try {
            Session sess = this.openSession();
            Query q = sess.createQuery(
                    "SELECT q FROM Room q ORDER BY q.idRoom DESC");
            List<Room> data = q.list();
            sess.close();
            return data;
        } catch (HibernateException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean insertData(Room param) {
        try {
            Session sess = this.openSession();
            Transaction t = sess.beginTransaction();
            sess.save(param);
            t.commit();
            sess.close();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public boolean updateData(Room param) {
        try {
            Session sess = this.openSession();
            Transaction t = sess.beginTransaction();
            sess.merge(param);
            t.commit();
            sess.close();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public boolean deleteData(Room param) {
        try {
            Session sess = this.openSession();
            param = getData(param.getIdRoom());
            Transaction t = sess.beginTransaction();
            sess.delete(param);
            t.commit();
            sess.close();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public Room getData(Object pk) {
        try {
            Session sess = this.openSession();
            Room data = (Room) sess.createQuery(
                    "SELECT q FROM Room q WHERE q.idRoom = " + pk.toString()).
                    list().get(0);
//            sess.close();
            return data;
        } catch (HibernateException e) {
            return new Room();
        }
    }
    
    public List<Room> getAllRoomAvailable() {
        Session sess = openSession();
        List<Room> temp = sess.createQuery("SELECT q FROM Room q WHERE q.stockRoom <> 0").list();
        sess.close();
        return temp;
    }
}
