/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import entitis.ChiTietSp;
import entitis.CuaHang;
import repositories.ChiTietSanPhamRepository;
import repositories.CuaHangRepository;
import services.ChiTietSanPhamService;
import services.CuaHangService;
import viewModel.ChiTietSanPhamViewModel;

import java.util.List;

/**
 *
 * @author anhkon
 */
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {
    private ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
    @Override
    public boolean them(ChiTietSp chiTietSp) {
        return chiTietSanPhamRepository.them(chiTietSp);
    }

    @Override
    public List<ChiTietSanPhamViewModel> getList() {
        return chiTietSanPhamRepository.getList();
    }
}
