/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import entities.MauSac;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import jakarta.persistence.Query;
import java.util.List;

/**
 * @author anhkon
 */
public class MauSacRepository {

    Session hSession = HibernateUtil.getFACTORY().openSession();


    public List<MauSac> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        String hql = "SELECT obj FROM MauSac obj";
        TypedQuery<MauSac> query = session.createQuery(hql, MauSac.class);
        return query.getResultList();
    }

    public boolean insert(MauSac mauSac) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            hSession.save(mauSac);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(String ma, MauSac mauSac) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            Query query = hSession.createQuery("update MauSac set  ten =:ten where ma = :ma");
            query.setParameter("ten", mauSac.getTen());
            query.setParameter("ma", ma);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(MauSac mauSac) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            hSession.delete(mauSac);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public MauSac findByMa(String ma) {
        String hql = "SELECT obj FROM MauSac obj WHERE obj.ma = :ma";
        TypedQuery<MauSac> query = hSession.createQuery(hql, MauSac.class);
        query.setParameter("ma", ma);
        if (query.getResultList().size() > 0) {
            return query.getSingleResult();
        } else {
            return null;
        }

    }

}
