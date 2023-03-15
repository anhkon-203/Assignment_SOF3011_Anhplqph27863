/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;


import entitis.DongSp;
import repositories.DongSpRepository;
import services.DongSpService;
import viewModel.DongSanPhamViewModel;

import java.util.List;

/**
 *
 * @author anhkon
 */
public class DongSpServiceImpl implements DongSpService {
    private DongSpRepository lstRepo = new DongSpRepository();
    @Override
    public boolean them(DongSp dongSp) {
        return lstRepo.them(dongSp);
    }

    @Override
    public boolean sua(String ma, DongSp dongSp) {
        return lstRepo.sua(ma, dongSp);
    }

    @Override
    public boolean xoa(DongSp dongSp) {
        return lstRepo.xoa(dongSp);
    }

    @Override
    public List<String> check(String ma) {
        return lstRepo.check(ma);
    }
    @Override
    public List<DongSp> getList() {
        return lstRepo.getList();
    }
    
}
