/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;


import entitis.SanPham;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.ConnectDB;
import viewModel.SanPhamViewModel;

import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author anhkon
 */
public class SanPhamRepository {

    public List<SanPham> getAll() {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("from SanPham");
        List<SanPham> lstSp = query.getResultList();
        return lstSp;

    }
   
    public boolean insert(SanPham sanPham) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
           session.save(sanPham);
           transaction.commit();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(String ma,SanPham sanPham) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
            Query query = session.createQuery("update SanPham set  ten =:ten where ma = :ma");
            query.setParameter("ten",sanPham.getTen());
            query.setParameter("ma",ma);
            query.executeUpdate();
           transaction.commit();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(SanPham sanPham) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
            Query query = session.createQuery("delete from SanPham  where  ma = :ma");
            query.setParameter("ma",sanPham.getMa());
            query.executeUpdate();
           transaction.commit();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public SanPham findByMa(String ma) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("from SanPham where ma = :ma");
        query.setParameter("ma",ma);
        SanPham sanPham = (SanPham) query.getSingleResult();
        return sanPham;
    }

}
