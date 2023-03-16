package repositories;

import entitis.NhanVien;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.ConnectDB;
import viewModel.NhanVienViewModel;

import javax.persistence.Query;
import java.util.List;

public class NhanVienRepository {
    public List<NhanVienViewModel> getAll() {
        String hql = "SELECT new viewModel.NhanVienViewModel(nv.ma, nv.ten, nv.tenDem, nv.ho, nv.gioiTinh, nv.ngaySinh, nv.diaChi, nv.sdt, nv.email, nv.cuaHang.ten, nv.chucVu.ten, nv.trangThai) FROM NhanVien nv";
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery(hql);
        List<NhanVienViewModel> lst = query.getResultList();
        return lst;
    }

    public boolean insert(NhanVien nhanVien) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(nhanVien);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    public boolean update(String ma,KhachHang khachHang) {
//        Transaction transaction = null;
//        try(Session session = ConnectDB.getFACTORY().openSession()) {
//            transaction = session.beginTransaction();
//            Query query = session.createQuery("update KhachHang set ten = :ten,ho =:ho,tenDem=:tenDem,ngaySinh=:ngaySinh" +
//                    ",sdt =:sdt,diaChi =:diaChi,thanhPho =:thanhPho,quocGia=:quocGia" +
//                    " where ma = :ma");
//            query.setParameter("ten",khachHang.getTen());
//            query.setParameter("ho",khachHang.getHo());
//            query.setParameter("tenDem",khachHang.getTenDem());
//            query.setParameter("ngaySinh",khachHang.getNgaySinh());
//            query.setParameter("sdt",khachHang.getSdt());
//            query.setParameter("diaChi",khachHang.getDiaChi());
//            query.setParameter("thanhPho",khachHang.getThanhPho());
//            query.setParameter("quocGia",khachHang.getQuocGia());
//            query.setParameter("ma",ma);
//            query.executeUpdate();
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    public boolean delete(NhanVien nhanVien) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
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
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select nv.cuaHang.id from  NhanVien nv where ma=:ma");
        query.setParameter("ma",ma);
        String maCuaHang = (String) query.getSingleResult();
        return maCuaHang;
    }
    public String findIdChucVuByMa(String ma) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select nv.chucVu.id from  NhanVien nv where ma=:ma");
        query.setParameter("ma",ma);
        String maChucVu = (String) query.getSingleResult();
        return maChucVu;
    }
    public NhanVien findByMa(String ma) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select nv from  NhanVien nv where ma=:ma");
        query.setParameter("ma",ma);
        NhanVien nhanVien = (NhanVien) query.getSingleResult();
        return nhanVien;
    }

}
