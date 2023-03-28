/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;
import entities.SanPham;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;
import java.util.List;

/**
 * @author anhkon
 */
public class SanPhamRepository {
    Session hsession = HibernateUtil.getFACTORY().openSession();

    Transaction transaction = hsession.getTransaction();

    public List<SanPham> findAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("from SanPham ");
        List<SanPham> lst = query.getResultList();
        return lst;

    }

    public boolean insert(SanPham sanPham) {
        try {
            transaction.begin();
            hsession.save(sanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public void delete(SanPham sanPham) {
        try {
            transaction.begin();
            this.hsession.delete(sanPham);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public boolean update(String ma, SanPham sanPham) {
        try {
            transaction.begin();
            Query query = hsession.createQuery("update SanPham set ten = :ten, srcImage = :src where ma = :ma");
            query.setParameter("ten", sanPham.getTen());
            query.setParameter("src", sanPham.getSrcImage());
            query.setParameter("ma", ma);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }


    public SanPham findByMa(String ma) {
        String hql = "SELECT obj FROM SanPham obj WHERE obj.ma = ?1";
        TypedQuery<SanPham> query = this.hsession.createQuery(hql, SanPham.class);
        query.setParameter(1, ma);
        if (query.getResultList().size() > 0) {
            return query.getSingleResult();
        } else {
            return null;
        }
    }
}
