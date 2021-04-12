/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.List;
import model.Pegawai;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Developer
 */
public class PegawaiDAOImpl implements DAOService<Pegawai> {

    private final SessionFactory session = HibernateUtil.getSessionFactory();

    @Override
    public Session openSession() {
        return session.openSession();
    }

    @Override
    public List<Pegawai> getAllData() {
        try {
            Session sess = this.openSession();
            Query q = sess.createQuery(
                    "SELECT q FROM Pegawai q ORDER BY q.idPegawai DESC");
            List<Pegawai> data = q.list();
            sess.close();
            return data;
        } catch (HibernateException e) {
            return new ArrayList<>();
        }
    }

    public List<Pegawai> getAllPegawai() {
        Session sess = openSession();
        List<Pegawai> temp = sess.createQuery("SELECT p FROM Pegawai p WHERE p.idJabatan.idJabatan = 1 or p.idJabatan.idJabatan = 2").list();
        sess.close();
        return temp;
    }

    @Override
    public boolean insertData(Pegawai param) {
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
    public boolean updateData(Pegawai param) {
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
    public boolean deleteData(Pegawai param) {
        try {
            Session sess = this.openSession();
            param = getData(param.getIdPegawai());
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
    public Pegawai getData(Object pk) {
        try {
            Session sess = this.openSession();
            Pegawai data = (Pegawai) sess.createQuery(
                    "SELECT q FROM Pegawai q WHERE q.idPegawai = " + Integer.parseInt(pk.toString())).
                    list().get(0);
//            sess.close();
            return data;
        } catch (HibernateException e) {
            return new Pegawai();
        }
    }
}
