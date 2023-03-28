package repositories;

import entities.KhachHang;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import jakarta.persistence.Query;
import java.util.List;

public class KhachHangRepository {
    Session session = HibernateUtil.getFACTORY().openSession();
    Transaction transaction = session.getTransaction();
    public List<KhachHang> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        String hql = "SELECT obj FROM KhachHang obj";
        TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
        return query.getResultList();
    }
    public KhachHang checkLogin(String email, String matKhau) {
        try {
            transaction.begin();
            String hql = "select k from KhachHang k where email = :sdt and matKhau = :matKhau";
            Query query = session.createQuery(hql);
            query.setParameter("sdt",email);
            query.setParameter("matKhau",matKhau);
            if (query.getResultList().isEmpty()) {
                return null;
            } else {
                return (KhachHang) query.getResultList().get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }
    public boolean insert(KhachHang khachHang) {
        try {
            transaction.begin();
            session.save(khachHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public boolean update(KhachHang khachHang) {
        try {
            transaction.begin();
            session.update(khachHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public boolean delete(KhachHang khachHang) {
        try {
            transaction.begin();
            session.delete(khachHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public KhachHang findByMa(String ma) {
       String hql = "select k from KhachHang k where k.ma = :ma";
       TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
         query.setParameter("ma", ma);
         if (query.getResultList().isEmpty()) {
             return null;
         } else {
             return query.getResultList().get(0);
         }
    }
}
