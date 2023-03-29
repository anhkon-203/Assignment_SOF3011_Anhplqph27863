package repositories;

import entities.ChiTietSp;
import entities.GioHang;
import entities.GioHangChiTiet;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;
import viewModel.GioHangChiTietViewModel;

import jakarta.persistence.Query;
import java.util.List;
import java.util.UUID;

public class GioHangRepository {

    Session hSession = HibernateUtil.getFACTORY().openSession();
    Transaction transaction = hSession.getTransaction();
    public List<GioHangChiTietViewModel> getAllGioHangChiTietByGioHangId(UUID idGioHang) {
        Session session = HibernateUtil.getFACTORY().openSession();
       String hql = "select new viewModel.GioHangChiTietViewModel(g.chiTietSp.Id,g.gioHang.Id ,g.chiTietSp.sanPham.ten,g.soLuongTon,g.donGia,g.chiTietSp.sanPham.srcImage) from GioHangChiTiet g where g.gioHang.Id = :idGioHang";
        TypedQuery<GioHangChiTietViewModel> query = session.createQuery(hql, GioHangChiTietViewModel.class);
        query.setParameter("idGioHang", idGioHang);
        return query.getResultList();
    }

    public boolean insertGioHangChiTiet(GioHangChiTiet gioHangChiTiet) {
        try {
            transaction.begin();
            // Kiểm tra xem sản phẩm đã có trong giỏ hàng hay chưa
            GioHangChiTiet existingGioHangChiTiet = (GioHangChiTiet) hSession.createQuery("FROM GioHangChiTiet WHERE gioHang.id = :idGioHang AND chiTietSp.id = :idChiTietSp")
                    .setParameter("idGioHang", gioHangChiTiet.getGioHang().getId())
                    .setParameter("idChiTietSp", gioHangChiTiet.getChiTietSp().getId())
                    .uniqueResult();
            if (existingGioHangChiTiet != null) {
                // Nếu sản phẩm đã có trong giỏ hàng, cập nhật số lượng sản phẩm
                existingGioHangChiTiet.setSoLuongTon(existingGioHangChiTiet.getSoLuongTon() + gioHangChiTiet.getSoLuongTon());
                hSession.update(existingGioHangChiTiet);
            } else {
                // Nếu sản phẩm chưa có trong giỏ hàng, thêm bản ghi mới
                hSession.save(gioHangChiTiet);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }
    public boolean insertGioHang(GioHang gioHang) {
        try {
            transaction.begin();
            hSession.save(gioHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }


    public boolean updateGioHangChiTiet(GioHangChiTiet gioHangChiTiet) {
        try {
            transaction.begin();
            hSession.update(gioHangChiTiet);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public boolean delete(UUID idGioHang, UUID idSanPham) {
        try {
            transaction.begin();
            GioHangChiTiet gioHangChiTiet = (GioHangChiTiet) hSession.createQuery("FROM GioHangChiTiet WHERE gioHang.id = :idGioHang AND chiTietSp.id = :idSanPham")
                    .setParameter("idGioHang", idGioHang)
                    .setParameter("idSanPham", idSanPham)
                    .uniqueResult();
            hSession.delete(gioHangChiTiet);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<GioHangChiTiet> getGioHangChiTietByIdGioHang(UUID idGioHang) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String hql = "select ghct from GioHangChiTiet ghct where ghct.gioHang.Id=:idGioHang";
        TypedQuery<GioHangChiTiet> query = session.createQuery(hql, GioHangChiTiet.class);
        query.setParameter("idGioHang", idGioHang);
        return query.getResultList();
    }

    public GioHangChiTiet getGioHangChiTietById(UUID id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("select ghct from GioHangChiTiet ghct where ghct.id=:id");
        query.setParameter("id", id);
        List<GioHangChiTiet> lst = query.getResultList();
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }

    public ChiTietSp getChiTietSp(UUID idChiTietSp) {
       String hql = "select ctsp from ChiTietSp ctsp where ctsp.Id=:idChiTietSp";
        TypedQuery<ChiTietSp> query = hSession.createQuery(hql, ChiTietSp.class);
        query.setParameter("idChiTietSp", idChiTietSp);
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return query.getResultList().get(0);
        }
    }

    public GioHang getGioHang(UUID idKhachHang) {
        String hql = "select gh from GioHang gh where gh.khachHang.Id=:idKhachHang and gh.trangThai=:trangThai";
        TypedQuery<GioHang> query = hSession.createQuery(hql, GioHang.class);
        query.setParameter("idKhachHang", idKhachHang);
        query.setParameter("trangThai", 0); // Giỏ hàng chưa thanh toán
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return query.getResultList().get(0);
        }
    }
}
