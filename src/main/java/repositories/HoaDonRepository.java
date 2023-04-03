package repositories;

import entities.HoaDon;
import entities.HoaDonChiTiet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;
import viewModel.HoaDonChiTietViewModel;

import java.util.List;
import java.util.UUID;

public class HoaDonRepository {
    Session hSession = HibernateUtil.getFACTORY().openSession();

    public List<HoaDonChiTietViewModel> getListByIdKH(UUID idKH, UUID HoaDonId) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("SELECT new viewModel.HoaDonChiTietViewModel(hd.hoaDon.id,hd.hoaDon.ma, hd.hoaDon.tenNguoiNhan, hd.hoaDon.diaChi, hd.hoaDon.sdt, hd.hoaDon.tinhTrang,hd.chiTietSp.sanPham.ten,hd.chiTietSp.sanPham.srcImage,hd.soLuongTon,hd.donGia) FROM HoaDonChiTiet hd WHERE hd.hoaDon.khachHang.id = :idKH and hd.hoaDon.id =: idHD GROUP BY hd.hoaDon.id, hd.hoaDon.ma, hd.hoaDon.tenNguoiNhan, hd.hoaDon.diaChi, hd.hoaDon.sdt, hd.hoaDon.tinhTrang,hd.chiTietSp.sanPham.ten,hd.chiTietSp.sanPham.srcImage,hd.soLuongTon,hd.donGia");
        query.setParameter("idKH", idKH);
        query.setParameter("idHD", HoaDonId);
        return query.getResultList();
    }
//    public List<HoaDonChiTietViewModel> getListHoaDon(UUID idKH) {
//        Session session = HibernateUtil.getFACTORY().openSession();
//        Query query = session.createQuery("SELECT new viewModel.HoaDonChiTietViewModel(hd.hoaDon.id,hd.hoaDon.ma, hd.hoaDon.tenNguoiNhan, hd.hoaDon.diaChi, hd.hoaDon.sdt, hd.hoaDon.tinhTrang,hd.chiTietSp.sanPham.ten,hd.chiTietSp.sanPham.srcImage,hd.soLuongTon,hd.donGia) FROM HoaDonChiTiet hd WHERE hd.hoaDon.khachHang.id = :idKH GROUP BY hd.hoaDon.id, hd.hoaDon.ma, hd.hoaDon.tenNguoiNhan, hd.hoaDon.diaChi, hd.hoaDon.sdt, hd.hoaDon.tinhTrang,hd.chiTietSp.sanPham.ten,hd.chiTietSp.sanPham.srcImage,hd.soLuongTon,hd.donGia");
//        query.setParameter("idKH", idKH);
//        return query.getResultList();
//    }
    public UUID getHoaDonByIdKH(UUID idKH) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("SELECT hd.id FROM HoaDon hd WHERE hd.khachHang.id = :idKH");
        query.setParameter("idKH", idKH);
        if (query.getResultList().size() > 0) {
            return (UUID) query.getResultList().get(0);
        } else {
            return null;
        }
    }
    public UUID insert(HoaDon hoaDon) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            UUID id = (UUID) session.save(hoaDon);
            transaction.commit();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertHDCT(HoaDonChiTiet hoaDonChiTiet) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(hoaDonChiTiet);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public HoaDon findById(UUID id) {
        {
            return this.hSession.find(HoaDon.class, id);
        }
    }
}
