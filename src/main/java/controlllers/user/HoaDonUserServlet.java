package controlllers.user;

import entities.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repositories.GioHangRepository;
import repositories.HoaDonRepository;
import viewModel.HoaDonChiTietViewModel;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@WebServlet(value = {"/HoaDonUserServlet/index", "/HoaDonUserServlet/store", "/HoaDonUserServlet/update", "/HoaDonUserServlet/delete"})
public class HoaDonUserServlet extends HttpServlet {
    private GioHangRepository gioHangRepository = new GioHangRepository();
    private HoaDonRepository hoaDonRepository = new HoaDonRepository();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("index")) {
            index(request, response);
        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("user");
        UUID idHoaDon = hoaDonRepository.getIDHoaDonByIdKH(khachHang.getId());
        List<HoaDonChiTietViewModel> listHoaDonChiTietViewModel = hoaDonRepository.getListByIdHoaDon(idHoaDon);

        // Tạo Map để lưu trữ các hoá đơn theo mã
        Map<String, List<HoaDonChiTietViewModel>> mapHoaDonChiTietViewModel = new HashMap<>();
        for (HoaDonChiTietViewModel hd : listHoaDonChiTietViewModel) {
            if (mapHoaDonChiTietViewModel.containsKey(hd.getMaHD())) {
                mapHoaDonChiTietViewModel.get(hd.getMaHD()).add(hd);
            } else {
                List<HoaDonChiTietViewModel> newList = new ArrayList<>();
                newList.add(hd);
                mapHoaDonChiTietViewModel.put(hd.getMaHD(), newList);
            }
        }

        request.setAttribute("mapHoaDonChiTietViewModel", mapHoaDonChiTietViewModel);
        request.getRequestDispatcher("/views/user/hoaDon/order.jsp").forward(request, response);
    }
    private String maHDCount() {
        String code = "";
        List<HoaDon> list = hoaDonRepository.getList();
        if (list.isEmpty()) {
            code = "HD00001";
        } else {
            int max = 0;
            for (HoaDon hd : list) {
                int so = Integer.parseInt(hd.getMa().substring(2));
                if (so > max) {
                    max = so;
                }
            }
            max++;
            if (max < 10) {
                code = "HD000" + max;
            } else if (max < 100) {
                code = "HD00" + max;
            } else if (max < 1000) {
                code = "HD0" + max;
            } else {
                code = "HD" + max;
            }
        }
        return code;
    }
    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // Lấy thông tin giỏ hàng từ session
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("user");
        GioHang gioHang = gioHangRepository.getGioHang(khachHang.getId());
        List<GioHangChiTiet> listGioHangChiTiet = gioHangRepository.getGioHangChiTietByIdGioHang(gioHang.getId());
        // Lấy thông tin khách hàng từ request
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        UUID id = UUID.fromString(request.getParameter("idKhachHang"));

        // Tạo đối tượng HoaDon và lưu vào cơ sở dữ liệu
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa(maHDCount());
        khachHang.setId(id);
        hoaDon.setKhachHang(khachHang);
        hoaDon.setTenNguoiNhan(name);
        hoaDon.setDiaChi(address);
        hoaDon.setSdt(phone);
        LocalDate localDate = LocalDate.now();
        hoaDon.setNgayTao(Date.valueOf(localDate));
        hoaDon.setTinhTrang(0);
        UUID hoaDonId = hoaDonRepository.insert(hoaDon);

// Lấy lại đối tượng HoaDon từ cơ sở dữ liệu
        HoaDon hoaDonSaved = hoaDonRepository.findById(hoaDonId);

// Tạo đối tượng HoaDonChiTiet cho mỗi sản phẩm trong giỏ hàng và lưu vào cơ sở dữ liệu
        if (listGioHangChiTiet != null) {
            for (GioHangChiTiet gioHangChiTiet : listGioHangChiTiet) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setHoaDon(hoaDonSaved);
                hoaDonChiTiet.setChiTietSp(gioHangChiTiet.getChiTietSp());
                hoaDonChiTiet.setSoLuongTon(gioHangChiTiet.getSoLuongTon());
                hoaDonChiTiet.setDonGia(gioHangChiTiet.getDonGia());
                hoaDonRepository.insertHDCT(hoaDonChiTiet);
            }
            gioHangRepository.deleteAll(gioHang.getId());
        }

        // Chuyển hướng đến trang cảm ơn
        response.sendRedirect(request.getContextPath() + "/HoaDonUserServlet/index");
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        }

    }
}
