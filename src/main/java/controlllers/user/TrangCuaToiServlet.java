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
import java.util.List;
import java.util.UUID;

@WebServlet(name = "TrangCuaToiServlet", value = "/TrangCuaToiServlet")
public class TrangCuaToiServlet extends HttpServlet {
    private GioHangRepository gioHangRepository = new GioHangRepository();
    private HoaDonRepository hoaDonRepository = new HoaDonRepository();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("user");
        List<HoaDonChiTietViewModel> listHoaDonChiTietViewModel = hoaDonRepository.getListByIdKH(khachHang.getId(),hoaDonRepository.getHoaDonByIdKH(khachHang.getId()));

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
