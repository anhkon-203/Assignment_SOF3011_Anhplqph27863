package repositories;

import entities.NhanVien;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;
import viewModel.NhanVienViewModel;

import jakarta.persistence.Query;
import java.util.List;

public class NhanVienRepository {
    public List<NhanVienViewModel> getAll() {
        String hql = "SELECT new viewModel.NhanVienViewModel(nv.ma, nv.ten, nv.tenDem, nv.ho, nv.gioiTinh, nv.ngaySinh, nv.diaChi, nv.sdt, nv.email, nv.cuaHang.ten, nv.chucVu.ten, nv.trangThai) FROM NhanVien nv";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(hql);
        List<NhanVienViewModel> lst = query.getResultList();
        return lst;
    }


    public boolean insert(NhanVien nhanVien) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(nhanVien);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(String ma,NhanVien nhanVien) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update NhanVien set ten =:ten, tenDem =:tenDem, ho =:ho, gioiTinh =:gioiTinh, ngaySinh =:ngaySinh, diaChi =:diaChi, sdt =:sdt, email =:email, cuaHang.id =:idCH, chucVu.id =:idChucVu, trangThai =:trangThai where ma = :ma");
            query.setParameter("ten",nhanVien.getTen());
            query.setParameter("tenDem",nhanVien.getTenDem());
            query.setParameter("ho",nhanVien.getHo());
            query.setParameter("gioiTinh",nhanVien.getGioiTinh());
            query.setParameter("ngaySinh",nhanVien.getNgaySinh());
            query.setParameter("diaChi",nhanVien.getDiaChi());
            query.setParameter("sdt",nhanVien.getSdt());
            query.setParameter("email",nhanVien.getEmail());
            query.setParameter("idCH",nhanVien.getCuaHang().getId());
            query.setParameter("idChucVu",nhanVien.getChucVu().getId());
            query.setParameter("trangThai",nhanVien.getTrangThai());
            query.setParameter("ma",ma);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(NhanVien nhanVien) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from NhanVien where  ma = :ma");
            query.setParameter("ma",nhanVien.getMa());
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public String findIdCuaHangByMa(String ma) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("select nv.cuaHang.id from  NhanVien nv where ma=:ma");
        query.setParameter("ma",ma);
        String maCuaHang = (String) query.getSingleResult();
        return maCuaHang;
    }
    public String findIdChucVuByMa(String ma) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("select nv.chucVu.id from  NhanVien nv where ma=:ma");
        query.setParameter("ma",ma);
        String maChucVu = (String) query.getSingleResult();
        return maChucVu;
    }
    public NhanVien findByMa(String ma) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("select nv from  NhanVien nv where ma=:ma");
        query.setParameter("ma",ma);
        NhanVien nhanVien = (NhanVien) query.getSingleResult();
        return nhanVien;
    }
    public NhanVien findEmail(String email) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("select nv from  NhanVien nv where email=:email");
        query.setParameter("email",email);
        NhanVien nhanVien = (NhanVien) query.getSingleResult();
        return nhanVien;
    }

}
