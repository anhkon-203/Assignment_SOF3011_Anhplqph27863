/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;


import entitis.ChiTietSp;
import entitis.CuaHang;
import entitis.DongSp;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.ConnectDB;
import viewModel.ChiTietSanPhamViewModel;

import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author anhkon
 */
public class ChiTietSanPhamRepository {

    public List<ChiTietSanPhamViewModel> getList() {
        String select = "select new viewModel.ChiTietSanPhamViewModel(ctsp.id,ctsp.namBaoHanh,ctsp.moTa,ctsp.soLuongTon,ctsp.giaNhap,ctsp.giaBan,ctsp.sanPham.ten,ctsp.nsx.ten,ctsp.mauSac.ten,ctsp.dongSp.ten) from ChiTietSp ctsp " ;
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery(select);
        List<ChiTietSanPhamViewModel> lstCtsp = query.getResultList();
        return lstCtsp;

    }
   
    public boolean insert(ChiTietSp chiTietSp) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
           session.save(chiTietSp);
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
    public boolean delete(ChiTietSp chiTietSp) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
            Query query = session.createQuery("delete from ChiTietSp  where  id = :id");
            query.setParameter("id",chiTietSp.getId());
            query.executeUpdate();
           transaction.commit();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public String getIdDongSp(String id){
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select c.dongSp.id from ChiTietSp c where id=:id");
        query.setParameter("id",id);
        String idDongSp = (String) query.getSingleResult();
        return idDongSp;
    }
    public String getIdMauSac(String id){
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select c.mauSac.id from ChiTietSp c where id=:id");
        query.setParameter("id",id);
        String idMauSac = (String) query.getSingleResult();
        return idMauSac;
    }
    public String getIdNhaSanXuat(String id){
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select c.nsx.id from ChiTietSp c where id=:id");
        query.setParameter("id",id);
        String idNhaSanXuat = (String) query.getSingleResult();
        return idNhaSanXuat;
    }
    public String getIdSanPham(String id){
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select c.sanPham.id from ChiTietSp c where id=:id");
        query.setParameter("id",id);
        String idSanPham = (String) query.getSingleResult();
        return idSanPham;
    }
    public ChiTietSp getById(String id) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("from ChiTietSp where id=:id");
        query.setParameter("id",id);
        ChiTietSp chiTietSp = (ChiTietSp) query.getSingleResult();
        return chiTietSp;
    }
//    public List<String> check(String ma) {
//        Session session = ConnectDB.getFACTORY().openSession();
//        Query query = session.createQuery("select ma from  SanPham  where ma=:ma");
//        query.setParameter("ma",ma);
//        List<String> results = query.getResultList();
//        return results;
//    }

}
