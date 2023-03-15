package services;

import entitis.KhachHang;

import java.util.List;

public interface KhachHangService {
    public List<KhachHang> getList();

    public boolean them(KhachHang khachHang);

    public boolean sua(String ma, KhachHang khachHang);

    public boolean xoa(KhachHang khachHang);

    public List<String> check(String ma);

}
