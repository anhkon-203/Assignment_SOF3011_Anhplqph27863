/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import entities.NSX;
import entities.SanPham;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import jakarta.persistence.Query;

import java.util.List;

/**
 * @author anhkon
 */
public class NSXRepository {

    Session hSession = HibernateUtil.getFACTORY().openSession();

    Transaction transaction = hSession.getTransaction();

    public List<NSX> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        TypedQuery<NSX> query = session.createQuery("select n from NSX n", NSX.class);
        List<NSX> lst = query.getResultList();
        return lst;

    }

    public boolean insert(NSX nsx) {
        try {
            transaction.begin();
            hSession.save(nsx);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public boolean update(String ma, NSX nsx) {
        try  {
            transaction.begin();
            Query query = hSession.createQuery("update NSX set ten =:ten where ma = :ma");
            query.setParameter("ten", nsx.getTen());
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

    public boolean delete(NSX nsx) {
        try  {
            transaction.begin();
            hSession.delete(nsx);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public NSX findByMa(String ma) {
        TypedQuery<NSX> query = hSession.createQuery("select n from NSX n where n.ma = :ma", NSX.class);
        query.setParameter("ma", ma);
        if (query.getResultList().size() > 0) {
            return query.getSingleResult();
        } else {
            return null;
        }

        }
    }

