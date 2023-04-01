/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import entities.ChucVu;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import jakarta.persistence.Query;

import java.util.List;

/**
 * @author anhkon
 */
public class ChucVuRepository {
    Session hSession = HibernateUtil.getFACTORY().openSession();


    public List<ChucVu> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        String hql = "Select obj From ChucVu obj";
        TypedQuery<ChucVu> chucVuTypedQuery = session.createQuery(hql, ChucVu.class);
        return chucVuTypedQuery.getResultList();

    }

    public boolean insert(ChucVu chucVu) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            hSession.save(chucVu);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public boolean update(String ma, ChucVu chucVu) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            Query query = hSession.createQuery("UPDATE ChucVu set ten=:ten where ma=:ma");
            query.setParameter("ten", chucVu.getTen());
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

    public void delete(ChucVu chucVu) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            hSession.delete(chucVu);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public ChucVu findByMa(String ma) {
        String hql = "Select obj From ChucVu obj where obj.ma =:ma";
        TypedQuery<ChucVu> chucVuTypedQuery = hSession.createQuery(hql, ChucVu.class);
        chucVuTypedQuery.setParameter("ma", ma);
        if (chucVuTypedQuery.getResultList().size() > 0) {
            return chucVuTypedQuery.getSingleResult();
        } else {
            return null;
        }
    }
}
