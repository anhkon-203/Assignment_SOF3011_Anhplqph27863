/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;


import entities.SanPham;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author anhkon
 */
public class SanPhamRepository {

    private Session hSession;

    public SanPhamRepository()
    {
        this.hSession = HibernateUtil.getFACTORY().openSession();
    }

    public boolean insert(SanPham sanPham) {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction = hSession.beginTransaction();
            hSession.persist(sanPham);
            transaction.commit();
            return true;
        } catch (ConstraintViolationException e) {
            // Thực hiện xử lý khi gặp lỗi unique key constraint
            e.printStackTrace();
            transaction.rollback();
            return false;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(SanPham sanPham) {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction = hSession.beginTransaction();
            hSession.merge(sanPham);
            transaction.commit();
            return true;
        } catch (ConstraintViolationException e) {
            // Thực hiện xử lý khi gặp lỗi unique key constraint
            e.printStackTrace();
            transaction.rollback();
            return false;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public void delete(SanPham sanPham)
    {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.delete(sanPham);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public SanPham findById(String id)
    {
        return this.hSession.find(SanPham.class, id);
    }

    public List<SanPham> findAll()
    {
        String hql = "SELECT obj FROM SanPham obj";
        TypedQuery<SanPham> query =  this.hSession.createQuery(hql, SanPham.class);
        return query.getResultList();
    }


    public SanPham findByMa(String ma)
    {
        String hql = "SELECT obj FROM SanPham obj WHERE obj.ma = ?1";
        TypedQuery<SanPham> query = this.hSession.createQuery(hql, SanPham.class);
        query.setParameter(1, ma);
        return query.getSingleResult();
    }
}
