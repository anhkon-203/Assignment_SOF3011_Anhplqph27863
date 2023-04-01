package repositories;

import entities.NhanVien;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;
import viewModel.NhanVienViewModel;

import jakarta.persistence.Query;

import java.util.List;
import java.util.UUID;

public class NhanVienRepository {
    Session hSession = HibernateUtil.getFACTORY().openSession();



    public List<NhanVienViewModel> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        String hql = "SELECT new viewModel.NhanVienViewModel(nv.ma, nv.ten, nv.tenDem, nv.ho, nv.gioiTinh, nv.ngaySinh, nv.diaChi, nv.sdt, nv.email, nv.cuaHang.ten, nv.chucVu.ten, nv.trangThai) FROM NhanVien nv";
        TypedQuery<NhanVienViewModel> query = session.createQuery(hql, NhanVienViewModel.class);
        return query.getResultList();
    }


    public boolean insert(NhanVien nhanVien) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            hSession.save(nhanVien);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public boolean update(String ma, NhanVien nhanVien) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            Query query = hSession.createQuery("update NhanVien set ten =:ten, tenDem =:tenDem, ho =:ho, gioiTinh =:gioiTinh, ngaySinh =:ngaySinh, diaChi =:diaChi, sdt =:sdt, email =:email, cuaHang.id =:idCH, chucVu.id =:idChucVu, trangThai =:trangThai where ma = :ma");
            query.setParameter("ten", nhanVien.getTen());
            query.setParameter("tenDem", nhanVien.getTenDem());
            query.setParameter("ho", nhanVien.getHo());
            query.setParameter("gioiTinh", nhanVien.getGioiTinh());
            query.setParameter("ngaySinh", nhanVien.getNgaySinh());
            query.setParameter("diaChi", nhanVien.getDiaChi());
            query.setParameter("sdt", nhanVien.getSdt());
            query.setParameter("email", nhanVien.getEmail());
            query.setParameter("idCH", nhanVien.getCuaHang().getId());
            query.setParameter("idChucVu", nhanVien.getChucVu().getId());
            query.setParameter("trangThai", nhanVien.getTrangThai());
            query.setParameter("ma", ma);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public boolean delete(NhanVien nhanVien) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            Query query = hSession.createQuery("delete from NhanVien where  ma = :ma");
            query.setParameter("ma", nhanVien.getMa());
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public UUID findIdCuaHangByMa(String ma) {
        Query query = hSession.createQuery("select nv.cuaHang.id from  NhanVien nv where ma=:ma");
        query.setParameter("ma", ma);
        UUID idCuaHang = (UUID) query.getSingleResult();
        return idCuaHang;
    }

    public UUID findIdChucVuByMa(String ma) {
        Query query = hSession.createQuery("select nv.chucVu.id from  NhanVien nv where ma=:ma");
        query.setParameter("ma", ma);
        UUID idChucVu = (UUID) query.getSingleResult();
        return idChucVu;
    }

    public NhanVien findByMa(String ma) {
        String hql = "select nv from  NhanVien nv where nv.ma=:ma";
        TypedQuery<NhanVien> query = hSession.createQuery(hql, NhanVien.class);
        query.setParameter("ma", ma);
        if (query.getResultList().size() > 0) {
            return query.getSingleResult();
        } else {
            return null;
        }
    }

    public NhanVien findEmail(String email) {

        TypedQuery<NhanVien> query = hSession.createQuery("select nv from  NhanVien nv where nv.email=:email", NhanVien.class);
        query.setParameter("email", email);
        if (query.getResultList().size() > 0) {
            return query.getSingleResult();
        } else {
            return null;
        }

    }


}
