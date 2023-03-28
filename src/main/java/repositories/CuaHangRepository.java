/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;


import entities.CuaHang;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import jakarta.persistence.Query;

import java.util.List;

/**
 * @author anhkon
 */
public class CuaHangRepository {
    Session hSession = HibernateUtil.getFACTORY().openSession();
    Transaction transaction = hSession.getTransaction();

    public List<CuaHang> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        String hql = "SELECT obj FROM CuaHang obj";
        TypedQuery<CuaHang> query = session.createQuery(hql, CuaHang.class);
        return query.getResultList();
    }

    public boolean insert(CuaHang cuaHang) {
        try {
            transaction.begin();
            hSession.save(cuaHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public boolean update(CuaHang cuaHang) {
        try {
            transaction.begin();
            hSession.update(cuaHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public boolean delete(CuaHang cuaHang) {
        try {
            transaction.begin();
            hSession.delete(cuaHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public CuaHang findByMa(String ma) {
        String hql = "SELECT obj FROM CuaHang obj WHERE obj.ma = :ma";
        TypedQuery<CuaHang> query = hSession.createQuery(hql, CuaHang.class);
        query.setParameter("ma", ma);
        if (query.getResultList().size() > 0) {
            return query.getResultList().get(0);
        } else {
            return null;
        }

    }
}
