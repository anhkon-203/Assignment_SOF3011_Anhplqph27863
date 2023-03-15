/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;


import entitis.SanPham;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.ConnectDB;

import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author anhkon
 */
public class SanPhamRepository {

    public List<SanPham> getList() {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery(" select s from SanPham s ORDER BY s.ma ASC ");
        List<SanPham> lstSp = query.getResultList();
        return lstSp;

    }
   
    public boolean them(SanPham sanPham) {
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
    
    public boolean sua(String ma,SanPham sanPham) {
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
    
    public boolean xoa(SanPham sanPham) {
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
    public List<String> check(String ma) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select ma from  SanPham  where ma=:ma");
        query.setParameter("ma",ma);
        List<String> results = query.getResultList();
        return results;
    }

    public static void main(String[] args) {
        List<SanPham> list = new SanPhamRepository().getList();
        for (SanPham x : list) {
            System.out.println(x.toString());
        }
    }
}
