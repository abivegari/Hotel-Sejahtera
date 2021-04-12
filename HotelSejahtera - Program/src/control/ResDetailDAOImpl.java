/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.List;
import model.ResDetail;
import model.Reservasi;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 *
 * @author Developer
 */
public class ResDetailDAOImpl implements DAOService<ResDetail> {

    private final SessionFactory session = HibernateUtil.getSessionFactory();

    public Session openSession() {
        return session.openSession();
    }

    public List<ResDetail> getAllResDetail() {
        Session sess = openSession();
        List<ResDetail> temp = sess.createQuery("SELECT p FROM ResDetail p WHERE p.rdId <> 0").list();
        sess.close();
        return temp;
    }

    public List<Reservasi> getAllReservasi() {
        try {
            Session sess = this.openSession();
            Criteria c = sess.createCriteria(Reservasi.class).addOrder(Order.desc("noReservasi"));
            List<Reservasi> data = c.list();
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean book(Reservasi param) {
        try {
            for (ResDetail rd : param.getResDetailList()) {
                rd.getIdRoom().setStockRoom((rd.getIdRoom().getStockRoom() - rd.getQuantity()));
                rd.setRoomPrice(rd.getIdRoom().getHarga());
            }

            Session sess = openSession();
            Transaction t = sess.beginTransaction();
            sess.save(param);

            for (ResDetail rd : param.getResDetailList()) {
                rd.setNoReservasi(param);
                sess.save(rd);
                sess.update(rd.getIdRoom());
            }

            t.commit();
            sess.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ResDetail> getAllData() {
        try {
            Session sess = this.openSession();
            Query q = sess.createQuery(
                    "SELECT q FROM ResDetail q ORDER BY q.rdId DESC");
            List<ResDetail> data = q.list();
            sess.close();
            return data;
        } catch (HibernateException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean insertData(ResDetail param) {
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
    public boolean updateData(ResDetail param) {
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
    public boolean deleteData(ResDetail param) {
        try {
            Session sess = this.openSession();
            param = getData(param.getRdId());
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
    public ResDetail getData(Object pk) {
        try {
            Session sess = this.openSession();
            ResDetail data = (ResDetail) sess.createQuery(
                    "SELECT q FROM ResDetail q WHERE q.rdId = " + pk.toString()).
                    list().get(0);
//            sess.close();
            return data;
        } catch (HibernateException e) {
            return new ResDetail();
        }
    }

    public List<ResDetail> getDataSearch(Integer noRes) {
        try {
            Session sess = openSession();
            String query = "SELECT p FROM ResDetail p";
            if (noRes != null) {
                query += " WHERE p.noReservasi like '%"+ noRes + "%'";
            }
            System.out.println(query);
            List<ResDetail> temp = sess.createQuery(query).list();
            return temp;
        } catch (HibernateException e) {
            return null;
        } catch (Exception ex) {
            return null;
        }
    }
}
