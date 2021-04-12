/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.List;
import model.Reservasi;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Developer
 */
public class ReservasiDAOImpl implements DAOService<Reservasi>{
    
    private final SessionFactory session = HibernateUtil.getSessionFactory();

    @Override
    public Session openSession() {
        return session.openSession();
    }

    @Override
    public List<Reservasi> getAllData() {
        try {
            Session sess = this.openSession();
            Query q = sess.createQuery(
                    "SELECT q FROM Reservasi q ORDER BY q.noReservasi DESC");
            List<Reservasi> data = q.list();
            sess.close();
            return data;
        } catch (HibernateException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean insertData(Reservasi param) {
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
    public boolean updateData(Reservasi param) {
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
    public boolean deleteData(Reservasi param) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Reservasi cat = new Reservasi();
            cat.setNoReservasi(param.getNoReservasi());

            session.delete(cat);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public Reservasi getData(Object pk) {
        try {
            Session sess = this.openSession();
            Reservasi data = (Reservasi) sess.createQuery(
                    "SELECT q FROM Reservasi q WHERE q.noReservasi = " + pk.toString()).
                    list().get(0);
//            sess.close();
            return data;
        } catch (HibernateException e) {
            return new Reservasi();
        }
    }
}
