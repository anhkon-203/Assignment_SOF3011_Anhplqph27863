/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;


import entities.ChiTietSp;
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
        String select = "select new viewModel.ChiTietSanPhamViewModel(ctsp.id,ctsp.namBaoHanh,ctsp.moTa,ctsp.soLuongTon,ctsp.giaNhap,ctsp.giaBan,ctsp.sanPham.ten,ctsp.nsx.ten,ctsp.mauSac.ten,ctsp.dongSp.ten,ctsp.sanPham.srcImage) from ChiTietSp ctsp  " ;
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery(select);
        List<ChiTietSanPhamViewModel> lstCtsp = query.getResultList();
        return lstCtsp;

    }
    public String selectTenDongSp(String idCtsp) {
        String select = "select ctsp.dongSp.ten from ChiTietSp ctsp where ctsp.id =:idCtsp" ;
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery(select);
        query.setParameter("idCtsp",idCtsp);
        String tenDongSp = query.getSingleResult().toString();
        return tenDongSp;

    }
    public List<ChiTietSanPhamViewModel> getListByTenDongsp(String tenDongSp) {
        String select = "select new viewModel.ChiTietSanPhamViewModel(ctsp.id,ctsp.namBaoHanh,ctsp.moTa,ctsp.soLuongTon,ctsp.giaNhap,ctsp.giaBan,ctsp.sanPham.ten,ctsp.nsx.ten,ctsp.mauSac.ten,ctsp.dongSp.ten,ctsp.sanPham.srcImage) from ChiTietSp ctsp where ctsp.dongSp.ten =:tenDongSp" ;
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery(select);
        query.setParameter("tenDongSp",tenDongSp);
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

    public boolean update(String id,ChiTietSp chiTietSp) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
           transaction = session.beginTransaction();
            Query query = session.createQuery("update ChiTietSp set namBaoHanh = :namBaoHanh, moTa = :moTa, soLuongTon = :soLuongTon, giaNhap = :giaNhap, giaBan = :giaBan, sanPham.id = :idSP, nsx.id = :idNSX, mauSac.id = :idMS, dongSp.id = :idDSP where id = :id");
            query.setParameter("namBaoHanh",chiTietSp.getNamBaoHanh());
            query.setParameter("moTa",chiTietSp.getMoTa());
            query.setParameter("soLuongTon",chiTietSp.getSoLuongTon());
            query.setParameter("giaNhap",chiTietSp.getGiaNhap());
            query.setParameter("giaBan",chiTietSp.getGiaBan());
            query.setParameter("idSP",chiTietSp.getSanPham().getId());
            query.setParameter("idNSX",chiTietSp.getNsx().getId());
            query.setParameter("idMS",chiTietSp.getMauSac().getId());
            query.setParameter("idDSP",chiTietSp.getDongSp().getId());
            query.setParameter("id",id);
            query.executeUpdate();
           transaction.commit();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
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
    public List<ChiTietSanPhamViewModel> userGetById(String id) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select new viewModel.ChiTietSanPhamViewModel(ctsp.id,ctsp.namBaoHanh,ctsp.moTa,ctsp.soLuongTon,ctsp.giaNhap,ctsp.giaBan,ctsp.sanPham.ten,ctsp.nsx.ten,ctsp.mauSac.ten,ctsp.dongSp.ten,ctsp.sanPham.srcImage) from ChiTietSp ctsp where id=:id");
        query.setParameter("id",id);
        List<ChiTietSanPhamViewModel> chiTietSp = query.getResultList();
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
