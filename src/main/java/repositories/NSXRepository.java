/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import entitis.NSX;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.ConnectDB;

import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author anhkon
 */
public class NSXRepository {

    public List<NSX> getList() {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery(" select n from NSX n ORDER BY n.ma ASC ");
        List<NSX> lst = query.getResultList();
        return lst;

    }
    
    public boolean them(NSX nsx) {
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
    
    public boolean sua(String ma,NSX nsx) {
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
    
    public boolean xoa(NSX nsx) {
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
    public List<String> check(String ma) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select ma from  NSX  where ma=:ma");
        query.setParameter("ma",ma);
        List<String> results = query.getResultList();
        return results;
    }

}
