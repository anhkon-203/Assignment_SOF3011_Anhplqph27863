package services.impl;

import entitis.NSX;
import repositories.NSXRepository;
import services.NhaSanXuatService;

import java.util.List;

public class NhaSanXuatServiceImpl implements NhaSanXuatService {
    private NSXRepository nsxRepository = new NSXRepository();
    @Override
    public List<NSX> getList() {
        return nsxRepository.getList();
    }

    @Override
    public boolean insert(NSX nsx) {
        return nsxRepository.them(nsx);
    }
}
