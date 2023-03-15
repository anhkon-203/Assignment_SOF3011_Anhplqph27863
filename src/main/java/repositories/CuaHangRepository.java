/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;


import entitis.CuaHang;
import entitis.SanPham;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.ConnectDB;
import viewModel.CuaHangViewModel;
import viewModel.SanPhamViewModel;

import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author anhkon
 */
public class CuaHangRepository {

    public List<CuaHang> getList() {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("from CuaHang");
        List<CuaHang> lstCuaHang = query.getResultList();
        return lstCuaHang;

    }
   
    public boolean them(CuaHang cuaHang) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
           session.save(cuaHang);
           transaction.commit();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
//    public boolean sua(String ma,SanPham sanPham) {
//        Transaction transaction = null;
//        try(Session session = ConnectDB.getFACTORY().openSession()) {
//           transaction = session.beginTransaction();
//            Query query = session.createQuery("update SanPham set  ten =:ten where ma = :ma");
//            query.setParameter("ten",sanPham.getTen());
//            query.setParameter("ma",ma);
//            query.executeUpdate();
//           transaction.commit();
//           return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean xoa(SanPham sanPham) {
//        Transaction transaction = null;
//        try(Session session = ConnectDB.getFACTORY().openSession()) {
//           transaction = session.beginTransaction();
//            Query query = session.createQuery("delete from SanPham  where  ma = :ma");
//            query.setParameter("ma",sanPham.getMa());
//            query.executeUpdate();
//           transaction.commit();
//           return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//    public List<String> check(String ma) {
//        Session session = ConnectDB.getFACTORY().openSession();
//        Query query = session.createQuery("select ma from  SanPham  where ma=:ma");
//        query.setParameter("ma",ma);
//        List<String> results = query.getResultList();
//        return results;
//    }

}
