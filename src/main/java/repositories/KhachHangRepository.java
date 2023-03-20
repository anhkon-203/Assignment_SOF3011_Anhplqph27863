package repositories;

import entities.KhachHang;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.ConnectDB;

import javax.persistence.Query;
import java.util.List;

public class KhachHangRepository {
    public List<KhachHang> getAll() {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("from KhachHang");
        List<KhachHang> lst = query.getResultList();
        return lst;
    }

    public boolean insert(KhachHang khachHang) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(khachHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(String ma,KhachHang khachHang) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update KhachHang set ten = :ten,ho =:ho,tenDem=:tenDem,ngaySinh=:ngaySinh" +
                    ",sdt =:sdt,diaChi =:diaChi,thanhPho =:thanhPho,quocGia=:quocGia" +
                    " where ma = :ma");
            query.setParameter("ten",khachHang.getTen());
            query.setParameter("ho",khachHang.getHo());
            query.setParameter("tenDem",khachHang.getTenDem());
            query.setParameter("ngaySinh",khachHang.getNgaySinh());
            query.setParameter("sdt",khachHang.getSdt());
            query.setParameter("diaChi",khachHang.getDiaChi());
            query.setParameter("thanhPho",khachHang.getThanhPho());
            query.setParameter("quocGia",khachHang.getQuocGia());
            query.setParameter("ma",ma);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(KhachHang khachHang) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from KhachHang where  ma = :ma");
            query.setParameter("ma",khachHang.getMa());
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public KhachHang findByMa(String ma) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select k from  KhachHang k  where ma=:ma");
        query.setParameter("ma",ma);
        KhachHang khachHang = (KhachHang) query.getSingleResult();
        return khachHang;
    }

}
