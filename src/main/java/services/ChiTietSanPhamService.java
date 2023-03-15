/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import entitis.ChiTietSp;
import entitis.CuaHang;
import viewModel.ChiTietSanPhamViewModel;

import java.util.List;

/**
 *
 * @author anhkon
 */
public interface ChiTietSanPhamService {

    public boolean them(ChiTietSp chiTietSp);

    public List<ChiTietSanPhamViewModel> getList();
}
