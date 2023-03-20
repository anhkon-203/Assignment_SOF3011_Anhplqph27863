/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import entities.MauSac;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.ConnectDB;

import javax.persistence.Query;
import java.util.List;

/**
 * @author anhkon
 */
public class MauSacRepository {

    public List<MauSac> getAll() {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery(
                "from MauSac");
        List<MauSac> lst = query.getResultList();
        return lst;

    }

    public boolean insert(MauSac mauSac) {
        Transaction transaction = null;
        try (Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(mauSac);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(String ma, MauSac mauSac) {
        Transaction transaction = null;
        try (Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update MauSac set  ten =:ten where ma = :ma");
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
        Transaction transaction = null;
        try (Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from MauSac  where  ma = :ma");
            query.setParameter("ma", mauSac.getMa());
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public MauSac findByMa(String ma) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select m from  MauSac m  where ma=:ma");
        query.setParameter("ma", ma);
        MauSac mauSac = (MauSac) query.getSingleResult();
        return mauSac;
    }

}
