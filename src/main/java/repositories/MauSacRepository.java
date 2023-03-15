/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import entitis.MauSac;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.ConnectDB;
import viewModel.MauSacViewModel;

import javax.persistence.Query;
import java.util.List;

/**
 * @author anhkon
 */
public class MauSacRepository {

    public List<MauSac> getList() {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery(
                "from MauSac");
        List<MauSac> lst = query.getResultList();
        return lst;

    }

    public boolean them(MauSac mauSac) {
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

    public boolean sua(String ma, MauSac mauSac) {
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

    public boolean xoa(MauSac mauSac) {
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

    public List<String> check(String ma) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select ma from  MauSac  where ma=:ma");
        query.setParameter("ma", ma);
        List<String> results = query.getResultList();
        return results;
    }

}
