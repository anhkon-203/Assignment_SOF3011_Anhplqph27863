/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import entitis.SanPham;
import repositories.SanPhamRepository;
import services.SanPhamService;

import java.util.List;

/**
 *
 * @author anhkon
 */
public class SanPhamServiceImpl implements SanPhamService {
    private SanPhamRepository lstRepo = new SanPhamRepository();
    @Override
    public boolean them(SanPham sanPham) {
      return lstRepo.them(sanPham);
    }

    @Override
    public boolean sua(String ma,SanPham sanPham) {
        return lstRepo.sua(ma,sanPham);
    }

    @Override
    public boolean xoa(SanPham sanPham) {
        return lstRepo.xoa(sanPham);
    }

    @Override
    public List<SanPham> getList() {
      return lstRepo.getList();
    }

    @Override
    public List<String> check(String ma) {
        return lstRepo.check(ma);
    }

}
