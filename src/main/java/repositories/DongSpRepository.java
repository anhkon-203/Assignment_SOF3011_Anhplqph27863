/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import entities.DongSp;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author anhkon
 */
public class DongSpRepository {

    public List<DongSp> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("from DongSp");
        List<DongSp> lst = query.getResultList();
        return lst;

    }
    
    public boolean insert(DongSp dongSp) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
           session.save(dongSp);
           transaction.commit();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(String ma,DongSp dongSp) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
            Query query = session.createQuery("update DongSp set  ten =:ten where ma = :ma");
            query.setParameter("ten",dongSp.getTen());
            query.setParameter("ma",ma);
            query.executeUpdate();
           transaction.commit();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(DongSp dongSp) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
            Query query = session.createQuery("delete from DongSp  where  ma = :ma");
            query.setParameter("ma",dongSp.getMa());
            query.executeUpdate();
           transaction.commit();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public DongSp findByMa(String ma) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("select d from  DongSp d  where ma=:ma");
        query.setParameter("ma",ma);
        List<DongSp> lst = query.getResultList();
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
}
