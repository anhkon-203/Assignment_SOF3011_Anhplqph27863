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
 * @author anhkon
 */
public class CuaHangRepository {

    public List<CuaHang> getAll() {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("from CuaHang");
        List<CuaHang> lstCuaHang = query.getResultList();
        return lstCuaHang;

    }

    public boolean insert(CuaHang cuaHang) {
        Transaction transaction = null;
        try (Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(cuaHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(String ma, CuaHang cuaHang) {
        Transaction transaction = null;
        try (Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update CuaHang set ten = :ten, diaChi = :diaChi, thanhPho =:thanhPho, quocGia =:quocGia where ma = :ma");
            query.setParameter("ten", cuaHang.getTen());
            query.setParameter("diaChi", cuaHang.getDiaChi());
            query.setParameter("thanhPho", cuaHang.getThanhPho());
            query.setParameter("quocGia", cuaHang.getQuocGia());
            query.setParameter("ma", ma);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(CuaHang cuaHang) {
        Transaction transaction = null;
        try (Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from CuaHang  where  ma = :ma");
            query.setParameter("ma", cuaHang.getMa());
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public CuaHang findByMa(String ma) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select c from  CuaHang c where ma=:ma");
        query.setParameter("ma", ma);
        CuaHang cuaHang = (CuaHang) query.getSingleResult();
        return cuaHang;
    }

}
