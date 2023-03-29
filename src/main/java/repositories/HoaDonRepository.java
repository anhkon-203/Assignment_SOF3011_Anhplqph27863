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

    public List<HoaDonChiTietViewModel> getListByIdKH(UUID idKH, UUID HoaDonId) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("SELECT new viewModel.HoaDonChiTietViewModel(hd.hoaDon.ma, hd.hoaDon.tenNguoiNhan, hd.hoaDon.diaChi, hd.hoaDon.sdt, hd.hoaDon.tinhTrang,hd.chiTietSp.sanPham.ten,hd.soLuongTon,hd.donGia) FROM HoaDonChiTiet hd WHERE hd.hoaDon.khachHang.id = :idKH and hd.hoaDon.id =: idHD GROUP BY hd.hoaDon.ma, hd.hoaDon.tenNguoiNhan, hd.hoaDon.diaChi, hd.hoaDon.sdt, hd.hoaDon.tinhTrang,hd.chiTietSp.sanPham.ten,hd.soLuongTon,hd.donGia");
        query.setParameter("idKH", idKH);
        query.setParameter("idHD", HoaDonId);
        return query.getResultList();
    }
    public HoaDon getHoaDonByIdKH(UUID idKH) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("SELECT hd FROM HoaDon hd WHERE hd.khachHang.id = :idKH");
        query.setParameter("idKH", idKH);
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return (HoaDon) query.getResultList().get(0);
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
}
