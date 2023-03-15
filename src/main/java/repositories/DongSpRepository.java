/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import entitis.DongSp;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.ConnectDB;

import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author anhkon
 */
public class DongSpRepository {

    public List<DongSp> getList() {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery(" select d from DongSp d ORDER BY d.ma ASC ");
        List<DongSp> lst = query.getResultList();
        return lst;

    }
    
    public boolean them(DongSp dongSp) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
           session.save(dongSp);
           transaction.commit();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean sua(String ma,DongSp dongSp) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
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
    
    public boolean xoa(DongSp dongSp) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
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
    public List<String> check(String ma) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select ma from  MauSac  where ma=:ma");
        query.setParameter("ma",ma);
        List<String> results = query.getResultList();
        return results;
    }

}
