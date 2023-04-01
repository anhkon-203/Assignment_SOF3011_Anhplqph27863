/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;


import entities.ChiTietSp;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;
import viewModel.ChiTietSanPhamViewModel;

import jakarta.persistence.Query;

import java.util.List;
import java.util.UUID;

/**
 * @author anhkon
 */
public class ChiTietSanPhamRepository {

    Session hSession = HibernateUtil.getFACTORY().openSession();


    public List<ChiTietSanPhamViewModel> getList() {
        Session session = HibernateUtil.getFACTORY().openSession();
        String hql = "select new viewModel.ChiTietSanPhamViewModel(ctsp.Id,ctsp.namBaoHanh,ctsp.moTa,ctsp.soLuongTon,ctsp.giaNhap,ctsp.giaBan,ctsp.sanPham.ten,ctsp.nsx.ten,ctsp.mauSac.ten,ctsp.dongSp.ten,ctsp.sanPham.srcImage) from ChiTietSp ctsp  ";
        TypedQuery<ChiTietSanPhamViewModel> query = session.createQuery(hql, ChiTietSanPhamViewModel.class);
        return query.getResultList();
    }

    public String selectTenDongSp(UUID idCtsp) {
        String hql = "select ctsp.dongSp.ten from ChiTietSp ctsp where ctsp.id =:idCtsp";
        Query query = hSession.createQuery(hql);
        query.setParameter("idCtsp", idCtsp);
        return (String) query.getSingleResult();

    }

    public List<ChiTietSanPhamViewModel> getListByTenDongsp(String tenDongSp) {
        String hql = "select new viewModel.ChiTietSanPhamViewModel(ctsp.Id,ctsp.namBaoHanh,ctsp.moTa,ctsp.soLuongTon,ctsp.giaNhap,ctsp.giaBan,ctsp.sanPham.ten,ctsp.nsx.ten,ctsp.mauSac.ten,ctsp.dongSp.ten,ctsp.sanPham.srcImage) from ChiTietSp ctsp where ctsp.dongSp.ten =:tenDongSp";
        TypedQuery<ChiTietSanPhamViewModel> query = hSession.createQuery(hql, ChiTietSanPhamViewModel.class);
        query.setParameter("tenDongSp", tenDongSp);
        return query.getResultList();
    }

    public boolean insert(ChiTietSp chiTietSp) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            hSession.save(chiTietSp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public boolean update(ChiTietSp chiTietSp) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            hSession.merge(chiTietSp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    //
    public boolean delete(ChiTietSp chiTietSp) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            hSession.delete(chiTietSp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public String getIdDongSp(UUID id) {
        Query query = hSession.createQuery("select c.dongSp.id from ChiTietSp c where id=:id");
        query.setParameter("id", id);
        return query.getSingleResult().toString();
    }

    public String getIdMauSac(UUID id) {
        Query query = hSession.createQuery("select c.mauSac.id from ChiTietSp c where id=:id");
        query.setParameter("id", id);
        return query.getSingleResult().toString();
    }

    public String getIdNhaSanXuat(UUID id) {
        Query query = hSession.createQuery("select c.nsx.id from ChiTietSp c where id=:id");
        query.setParameter("id", id);
        return query.getSingleResult().toString();
    }

    public String getIdSanPham(UUID id) {
        Query query = hSession.createQuery("select c.sanPham.id from ChiTietSp c where id=:id");
        query.setParameter("id", id);
        return query.getSingleResult().toString();
    }

    public ChiTietSp getById(UUID id) {
        Query query = hSession.createQuery("from ChiTietSp where id=:id");
        query.setParameter("id", id);
        if (query.getResultList().size() > 0) {
            return (ChiTietSp) query.getResultList().get(0);
        }else {
            return null;
        }
    }

    public List<ChiTietSanPhamViewModel> userGetById(UUID id) {
        TypedQuery<ChiTietSanPhamViewModel> query = hSession.createQuery("select new viewModel.ChiTietSanPhamViewModel(ctsp.id,ctsp.namBaoHanh,ctsp.moTa,ctsp.soLuongTon,ctsp.giaNhap,ctsp.giaBan,ctsp.sanPham.ten,ctsp.nsx.ten,ctsp.mauSac.ten,ctsp.dongSp.ten,ctsp.sanPham.srcImage) from ChiTietSp ctsp where id=:id");
        query.setParameter("id", id);
        return query.getResultList();

    }
//    public List<String> check(String ma) {
//        Session session = HibernateUtil.getFACTORY().openSession();
//        Query query = session.createQuery("select ma from  SanPham  where ma=:ma");
//        query.setParameter("ma",ma);
//        List<String> results = query.getResultList();
//        return results;
//    }

}
