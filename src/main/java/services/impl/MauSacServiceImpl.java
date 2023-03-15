/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;


import entitis.MauSac;
import repositories.MauSacRepository;
import services.MauSacService;
import viewModel.MauSacViewModel;

import java.util.List;

/**
 *
 * @author anhkon
 */
public class MauSacServiceImpl implements MauSacService {
    private MauSacRepository lstRepo = new MauSacRepository();
    @Override
    public boolean them(MauSac mauSac) {
        return lstRepo.them(mauSac);
    }

    @Override
    public boolean sua(String ma, MauSac mauSac) {
        return lstRepo.sua(ma, mauSac);
    }

    @Override
    public boolean xoa(MauSac mauSac) {
        return lstRepo.xoa(mauSac);
    }

    @Override
    public List<String> check(String ma) {
        return lstRepo.check(ma);
    }

    @Override
    public List<MauSac> getList() {
        return lstRepo.getList();
    }
    
}
