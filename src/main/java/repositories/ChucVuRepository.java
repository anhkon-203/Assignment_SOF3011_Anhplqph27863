/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import entities.ChucVu;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.ConnectDB;

import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author anhkon
 */
public class ChucVuRepository {

    public List<ChucVu> getAll() {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("from ChucVu ");
        List<ChucVu> lst = query.getResultList();
        return lst;

    }
    
    public boolean insert(ChucVu chucVu) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
           session.save(chucVu);
           transaction.commit();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(String ma,ChucVu chucVu) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
            Query query = session.createQuery("update ChucVu set ten =:ten where ma = :ma");
            query.setParameter("ten",chucVu.getTen());
            query.setParameter("ma",ma);
            query.executeUpdate();
           transaction.commit();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete( ChucVu chucVu) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
            Query query = session.createQuery("delete from ChucVu  where  ma = :ma");
            query.setParameter("ma",chucVu.getMa());
            query.executeUpdate();
           transaction.commit();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public ChucVu findByMa(String ma) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select c from  ChucVu c where ma=:ma");
        query.setParameter("ma",ma);
        ChucVu chucVu = (ChucVu) query.getSingleResult();
        return chucVu;
    }
}
