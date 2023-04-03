package controlllers.user;

import entities.GioHang;
import entities.HoaDon;
import entities.KhachHang;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repositories.ChiTietSanPhamRepository;
import repositories.GioHangRepository;
import repositories.HoaDonRepository;
import viewModel.ChiTietSanPhamViewModel;
import viewModel.GioHangChiTietViewModel;
import viewModel.HoaDonChiTietViewModel;

import java.io.IOException;
import java.util.*;

@WebServlet(value = {"/TrangCuaToiServlet/index", "/TrangCuaToiServlet/detail", "/TrangCuaToiServlet/update"})
public class TrangCuaToiServlet extends HttpServlet {
    private GioHangRepository gioHangRepository = new GioHangRepository();
    private HoaDonRepository hoaDonRepository = new HoaDonRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("index")) {
            this.index(request, response);
        } else if (uri.contains("detail")) {
            this.detail(request, response);
        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("user");
        List<HoaDonChiTietViewModel> listHoaDonChiTietViewModel = hoaDonRepository.getListByIdKH(khachHang.getId());

        String realPath = request.getServletContext().getRealPath("/images");
        // Thay đổi đường dẫn tới ảnh để hiển thị ảnh thay vì đường dẫn

        for (HoaDonChiTietViewModel hoaDonChiTietViewModel : listHoaDonChiTietViewModel) {
            String fileName = hoaDonChiTietViewModel.getSrcImage();
            if (fileName != null) {
                hoaDonChiTietViewModel.setSrcImage(request.getContextPath() + "/images/" + fileName);
            }
        }
        request.setAttribute("listHoaDonChiTietViewModel", listHoaDonChiTietViewModel);
        request.setAttribute("view", "/views/user/trangCuaToi.jsp");
        request.getRequestDispatcher("/views/user/layoutUser.jsp").forward(request, response);
    }

    protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maHD = request.getParameter("maHD");
        List<HoaDonChiTietViewModel> listHoaDonChiTietViewModel = hoaDonRepository.findByMaHD(maHD);

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
        request.setAttribute("view", "/views/user/hoaDon/thongTinSP.jsp");
        request.getRequestDispatcher("/views/user/layoutUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.update(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maHD = request.getParameter("maHD");
       hoaDonRepository.updateTrangThaiHoaDon(maHD,1);
        response.sendRedirect(request.getContextPath() + "/TrangCuaToiServlet/index");
    }
}
