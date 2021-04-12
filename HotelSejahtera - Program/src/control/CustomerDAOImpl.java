/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Developer
 */
public class CustomerDAOImpl implements DAOService<Customer>{
    private final SessionFactory session = HibernateUtil.getSessionFactory();

    @Override
    public Session openSession() {
        return session.openSession();
    }

    @Override
    public List<Customer> getAllData() {
        try {
            Session sess = this.openSession();
            Query q = sess.createQuery(
                    "SELECT q FROM Customer q ORDER BY q.noKtpCustomer DESC");
            List<Customer> data = q.list();
            sess.close();
            return data;
        } catch (HibernateException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean insertData(Customer param) {
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
    public boolean updateData(Customer param) {
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
    public boolean deleteData(Customer param) {
        try {
            Session sess = this.openSession();
            param = getData(Integer.parseInt(param.getNoKtpCustomer()));
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
    public Customer getData(Object pk) {
        try {
            Session sess = this.openSession();
            Query q = sess.createQuery("SELECT c FROM Customer c WHERE c.noKtpCustomer = '" + pk.toString() + "'");
            Customer data = (Customer) q.list().get(0);
            sess.close();
            return data;
        } catch (HibernateException e) {
            return new Customer();
        }
    }
    
    public List<Customer> searchKtp(String ktp) {
        try {
            Session sess = openSession();
            String query = "SELECT p FROM Customer p";
            if (!ktp.isEmpty()) {
                query += " WHERE p.noKtpCustomer " + ktp;
            }
            System.out.println(query);
            List<Customer> temp = sess.createQuery(query).list();
            return temp;
        } catch (HibernateException e) {
            return null;
        } catch (Exception ex) {
            return null;
        }
    }
}
