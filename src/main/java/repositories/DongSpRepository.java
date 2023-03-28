/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import entities.DongSp;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import jakarta.persistence.Query;

import java.util.List;

/**
 * @author anhkon
 */
public class DongSpRepository {

    Session hSession = HibernateUtil.getFACTORY().openSession();

    Transaction transaction = hSession.getTransaction();

    public List<DongSp> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        String hql = "SELECT obj FROM DongSp obj";
        TypedQuery<DongSp> query = session.createQuery(hql, DongSp.class);
        return query.getResultList();
    }

    public boolean insert(DongSp dongSp) {
        try {
            transaction.begin();
            hSession.save(dongSp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public void delete(DongSp dongSp) {
        try {
            transaction.begin();
            this.hSession.delete(dongSp);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public boolean update(String ma, DongSp dongSp) {
        try {
            Query query = hSession.createQuery("update DongSp set ten = :ten where ma = :ma");
            query.setParameter("ten", dongSp.getTen());
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


    public DongSp findByMa(String ma) {
        String hql = "SELECT obj FROM DongSp obj WHERE obj.ma = ?1";
        TypedQuery<DongSp> query = this.hSession.createQuery(hql, DongSp.class);
        query.setParameter(1, ma);
        if (query.getResultList().size() > 0) {
            return query.getSingleResult();
        } else {
            return null;
        }
    }
}
