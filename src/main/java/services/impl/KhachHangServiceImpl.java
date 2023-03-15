package services.impl;

import entitis.KhachHang;
import repositories.KhachHangRepository;
import services.KhachHangService;
import viewModel.KhachHangViewModel;

import java.util.List;

public class KhachHangServiceImpl implements KhachHangService {
    private KhachHangRepository khachHangRepository = new KhachHangRepository();
    @Override
    public List<KhachHang> getList() {
        return khachHangRepository.getList();
    }

    @Override
    public boolean them(KhachHang khachHang) {
        return khachHangRepository.them(khachHang);
    }

    @Override
    public boolean sua(String ma, KhachHang khachHang) {
        return khachHangRepository.sua(ma, khachHang);
    }

    @Override
    public boolean xoa(KhachHang khachHang) {
        return khachHangRepository.xoa(khachHang);
    }

    @Override
    public List<String> check(String ma) {
        return khachHangRepository.check(ma);
    }
}
