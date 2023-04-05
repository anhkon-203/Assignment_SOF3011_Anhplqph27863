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

    public List<GioHangChiTietViewModel> getAllGioHangChiTietByGioHangId(UUID idGioHang) {
        Session session = HibernateUtil.getFACTORY().openSession();
       String hql = "select new viewModel.GioHangChiTietViewModel(g.chiTietSp.Id,g.gioHang.Id ,g.chiTietSp.sanPham.ten,g.soLuongTon,g.donGia,g.chiTietSp.sanPham.srcImage) from GioHangChiTiet g where g.gioHang.Id = :idGioHang";
        TypedQuery<GioHangChiTietViewModel> query = session.createQuery(hql, GioHangChiTietViewModel.class);
        query.setParameter("idGioHang", idGioHang);
        return query.getResultList();
    }

//    public boolean insertGioHangChiTiet(GioHangChiTiet gioHangChiTiet) {
//        Transaction transaction = hSession.getTransaction();
//        try {
//            transaction.begin();
//            // Kiểm tra xem sản phẩm đã có trong giỏ hàng hay chưa
//            GioHangChiTiet existingGioHangChiTiet = (GioHangChiTiet) hSession.createQuery("FROM GioHangChiTiet WHERE gioHang.id = :idGioHang AND chiTietSp.id = :idChiTietSp")
//                    .setParameter("idGioHang", gioHangChiTiet.getGioHang().getId())
//                    .setParameter("idChiTietSp", gioHangChiTiet.getChiTietSp().getId())
//                    .uniqueResult();
//            if (existingGioHangChiTiet != null) {
//                // Nếu sản phẩm đã có trong giỏ hàng, cập nhật số lượng sản phẩm
//                existingGioHangChiTiet.setSoLuongTon(existingGioHangChiTiet.getSoLuongTon() + gioHangChiTiet.getSoLuongTon());
//                hSession.merge(existingGioHangChiTiet);
//            } else {
//                // Nếu sản phẩm chưa có trong giỏ hàng, thêm bản ghi mới
//                hSession.save(gioHangChiTiet);
//            }
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            transaction.rollback();
//            return false;
//        }
//    }

    public boolean insertGioHangChiTiet(GioHangChiTiet gioHangChiTiet) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            String hql = "FROM GioHangChiTiet WHERE gioHang.id = :idGioHang AND chiTietSp.id = :idChiTietSp";
            Query query = hSession.createQuery(hql);
            query.setParameter("idGioHang", gioHangChiTiet.getGioHang().getId());
            query.setParameter("idChiTietSp", gioHangChiTiet.getChiTietSp().getId());
            List<GioHangChiTiet> gioHangChiTietList = query.getResultList();
            GioHangChiTiet existingGioHangChiTiet = null;
            if (!gioHangChiTietList.isEmpty()) {
                existingGioHangChiTiet = gioHangChiTietList.get(0);
            }
            if (existingGioHangChiTiet != null) {
                existingGioHangChiTiet.setSoLuongTon(existingGioHangChiTiet.getSoLuongTon() + gioHangChiTiet.getSoLuongTon());
                hSession.merge(existingGioHangChiTiet);
            } else {
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
        Transaction transaction = hSession.getTransaction();
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
        Transaction transaction = hSession.getTransaction();
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
    public boolean deleteAll(UUID idGioHang) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            Query query = hSession.createQuery("DELETE FROM GioHangChiTiet WHERE gioHang.id = :idGioHang");
            query.setParameter("idGioHang", idGioHang);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean delete(UUID idGioHang, UUID idSanPham) {
        Transaction transaction = hSession.getTransaction();
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


    public GioHangChiTiet getGHCTObjByIdGioHang(UUID idGioHang) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String hql = "select ghct from GioHangChiTiet ghct where ghct.gioHang.Id=:idGioHang";
        TypedQuery<GioHangChiTiet> query = session.createQuery(hql, GioHangChiTiet.class);
        query.setParameter("idGioHang", idGioHang);
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return query.getResultList().get(0);
        }
    }
    public Integer getSoLuong(UUID idGioHang, UUID idSanPham) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String hql = "select ghct.soLuongTon from GioHangChiTiet ghct where ghct.gioHang.Id=:idGioHang and ghct.chiTietSp.Id=:idSanPham";
        TypedQuery<Integer> query = session.createQuery(hql, Integer.class);
        query.setParameter("idGioHang", idGioHang);
        query.setParameter("idSanPham", idSanPham);
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return query.getResultList().get(0);
        }
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
