package services;

import entitis.NSX;

import java.util.List;

public interface NhaSanXuatService {
    public List<NSX> getList();

    public boolean insert(NSX nsx);
}
