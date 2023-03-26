package repositories;

import entities.ChiTietSp;
import entities.GioHang;
import entities.GioHangChiTiet;
import entities.KhachHang;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.ConnectDB;
import viewModel.ChiTietSanPhamViewModel;
import viewModel.GioHangChiTietViewModel;

import javax.persistence.Query;
import java.util.List;

public class GioHangRepository {

    public List<GioHangChiTietViewModel> getAllGioHangChiTietByGioHangId(String idGioHang) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select new viewModel.GioHangChiTietViewModel(g.chiTietSp.id,g.gioHang.id ,g.chiTietSp.sanPham.ten,g.soLuongTon,g.donGia,g.chiTietSp.sanPham.srcImage) from GioHangChiTiet g where g.gioHang.id = :idGioHang");
        query.setParameter("idGioHang", idGioHang);
        List<GioHangChiTietViewModel> lst = query.getResultList();
        return lst;
    }

    public boolean insertGioHangChiTiet(GioHangChiTiet gioHangChiTiet) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(gioHangChiTiet);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean insertGioHang(GioHang gioHang) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(gioHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateGioHangChiTiet(GioHangChiTiet gioHangChiTiet) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.update(gioHangChiTiet);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String idGioHang, String idSanPham) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            GioHangChiTiet gioHangChiTiet = (GioHangChiTiet) session.createQuery("FROM GioHangChiTiet WHERE gioHang.id = :idGioHang AND chiTietSp.id = :idSanPham")
                    .setParameter("idGioHang", idGioHang)
                    .setParameter("idSanPham", idSanPham)
                    .uniqueResult();
            session.delete(gioHangChiTiet);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<GioHangChiTiet> getGioHangChiTietByIdGioHang(String idGioHang, String idChiTietSp) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select ghct from GioHangChiTiet ghct where ghct.gioHang.id=:idGioHang and ghct.chiTietSp.id=:idChiTietSp");
        query.setParameter("idGioHang", idGioHang);
        query.setParameter("idChiTietSp", idChiTietSp);
        List<GioHangChiTiet> lst = query.getResultList();
        return lst;
    }

    public GioHangChiTiet getGioHangChiTietById(String id) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select ghct from GioHangChiTiet ghct where ghct.id=:id");
        query.setParameter("id", id);
        List<GioHangChiTiet> lst = query.getResultList();
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }

    public ChiTietSp getChiTietSp(String idChiTietSp) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select ctsp from ChiTietSp ctsp where ctsp.id=:idChiTietSp");
        query.setParameter("idChiTietSp", idChiTietSp);
        List<ChiTietSp> lst = query.getResultList();
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }

    public GioHang getGioHang(String idKhachHang) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select gh from GioHang gh where gh.khachHang.id=:idKhachHang and gh.trangThai=:trangThai");
        query.setParameter("idKhachHang", idKhachHang);
        query.setParameter("trangThai", 0); // Giỏ hàng chưa thanh toán
        List<GioHang> gioHang = query.getResultList();
        if (gioHang.isEmpty()) {
            return null;
        } else {
            return gioHang.get(0);
        }
    }
}
