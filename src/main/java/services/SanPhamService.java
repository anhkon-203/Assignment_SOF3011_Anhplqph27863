/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import entitis.SanPham;

import java.util.List;

/**
 *
 * @author anhkon
 */
public interface SanPhamService {

    public boolean them(SanPham sanPham);

    public boolean sua(String ma, SanPham sanPham);

    public boolean xoa(SanPham sanPham);

    public List<String> check(String ma);

    public List<SanPham> getList();
}
