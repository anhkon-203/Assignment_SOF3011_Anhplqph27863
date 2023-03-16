/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import entitis.NSX;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.ConnectDB;
import viewModel.NSXViewModel;

import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author anhkon
 */
public class NSXRepository {

    public List<NSX> getAll() {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("from NSX");
        List<NSX> lst = query.getResultList();
        return lst;

    }
    
    public boolean insert(NSX nsx) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
           session.save(nsx);
           transaction.commit();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(String ma,NSX nsx) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
            Query query = session.createQuery("update NSX set ten =:ten where ma = :ma");
            query.setParameter("ten",nsx.getTen());
            query.setParameter("ma",ma);
            query.executeUpdate();
           transaction.commit();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(NSX nsx) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
            Query query = session.createQuery("delete from NSX  where  ma = :ma");
            query.setParameter("ma",nsx.getMa());
            query.executeUpdate();
           transaction.commit();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public NSX findByMa(String ma) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select n from  NSX n where ma=:ma");
        query.setParameter("ma",ma);
        NSX nsx = (NSX) query.getSingleResult();
        return nsx;
    }
}
