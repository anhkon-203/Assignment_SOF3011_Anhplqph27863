/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import entitis.CuaHang;
import entitis.SanPham;
import viewModel.CuaHangViewModel;
import viewModel.SanPhamViewModel;

import java.util.List;

/**
 *
 * @author anhkon
 */
public interface CuaHangService {

    public boolean them(CuaHang cuaHang);

    public List<CuaHang> getList();
}
