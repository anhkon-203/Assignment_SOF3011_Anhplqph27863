/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import entitis.CuaHang;
import repositories.CuaHangRepository;
import services.CuaHangService;
import viewModel.CuaHangViewModel;

import java.util.List;

/**
 *
 * @author anhkon
 */
public class CuaHangServiceImpl implements CuaHangService {
    private CuaHangRepository cuaHangRepository = new CuaHangRepository();
    @Override
    public boolean them(CuaHang cuaHang) {
        return cuaHangRepository.them(cuaHang);
    }

    @Override
    public List<CuaHang> getList() {
        return cuaHangRepository.getList();
    }
}
