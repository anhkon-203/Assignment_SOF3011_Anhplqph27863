package repositories;

import entitis.KhachHang;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.ConnectDB;
import viewModel.KhachHangViewModel;

import javax.persistence.Query;
import java.util.List;

public class KhachHangRepository {
    public List<KhachHang> getList() {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("from KhachHang");
        List<KhachHang> lst = query.getResultList();
        return lst;
    }

    public boolean them(KhachHang khachHang) {
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

    public boolean sua(String ma,KhachHang khachHang) {
        Transaction transaction = null;
        try(Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update KhachHang set  ten =:ten where ma = :ma");
            query.setParameter("ten",khachHang.getTen());
            query.setParameter("ma",ma);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoa(KhachHang khachHang) {
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
    public List<String> check(String ma) {
        Session session = ConnectDB.getFACTORY().openSession();
        Query query = session.createQuery("select ma from  KhachHang  where ma=:ma");
        query.setParameter("ma",ma);
        List<String> results = query.getResultList();
        return results;
    }

}
