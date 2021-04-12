/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.List;
import model.Kamar;
import model.Kamar;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Developer
 */
public class KamarDAOImpl implements DAOService<Kamar>{
    private final SessionFactory session = HibernateUtil.getSessionFactory();

    @Override
    public Session openSession() {
        return session.openSession();
    }

    @Override
    public List<Kamar> getAllData() {
        try {
            Session sess = this.openSession();
            Query q = sess.createQuery(
                    "SELECT q FROM Kamar q ORDER BY q.idKamar DESC");
            List<Kamar> data = q.list();
            sess.close();
            return data;
        } catch (HibernateException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean insertData(Kamar param) {
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
    public boolean updateData(Kamar param) {
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
    public boolean deleteData(Kamar param) {
        try {
            Session sess = this.openSession();
            param = getData(param.getIdKamar());
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
    public Kamar getData(Object pk) {
        try {
            Session sess = this.openSession();
            Kamar data = (Kamar) sess.createQuery(
                    "SELECT q FROM Kamar q WHERE q.idKamar = " + Integer.parseInt(pk.toString())).
                    list().get(0);
//            sess.close();
            return data;
        } catch (HibernateException e) {
            return new Kamar();
        }
    }
}
