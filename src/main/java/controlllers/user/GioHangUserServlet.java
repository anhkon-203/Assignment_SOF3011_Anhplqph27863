package controlllers.user;

import entities.ChiTietSp;
import entities.GioHang;
import entities.GioHangChiTiet;
import entities.KhachHang;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repositories.GioHangRepository;
import viewModel.GioHangChiTietViewModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@WebServlet({"/GioHangUserServlet/index", "/GioHangUserServlet/store", "/GioHangUserServlet/update", "/GioHangUserServlet/delete"})
public class GioHangUserServlet extends HttpServlet {
    private GioHangRepository gioHangRepository = new GioHangRepository();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("index")) {
            showGioHang(request, response);
        } else if (uri.contains("delete")) {
            deleteGioHang(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            storeGioHang(request, response);
        } else if (uri.contains("update")) {
            updateGioHang(request, response);
        }
    }

    protected void showGioHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("user");
        GioHang gioHang = gioHangRepository.getGioHang(khachHang.getId());
        List<GioHangChiTietViewModel> listGioHangChiTiet = gioHangRepository.getAllGioHangChiTietByGioHangId(gioHang.getId());

        String realPath = request.getServletContext().getRealPath("/images");
        // Thay đổi đường dẫn tới ảnh để hiển thị ảnh thay vì đường dẫn

        for (GioHangChiTietViewModel gioHangChiTietViewModel : listGioHangChiTiet) {
            String fileName = gioHangChiTietViewModel.getSrcImage();
            if (fileName != null) {
                gioHangChiTietViewModel.setSrcImage(request.getContextPath() + "/images/" + fileName);
            }
        }
        request.setAttribute("listGioHangChiTiet", listGioHangChiTiet);
        request.setAttribute("view", "/views/user/gioHang/gioHang.jsp");
        request.getRequestDispatcher("/views/user/layoutUser.jsp").forward(request, response);
    }


    protected void storeGioHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("user");
        GioHang gioHang = gioHangRepository.getGioHang(khachHang.getId());
        UUID idChiTietSp = UUID.fromString(request.getParameter("id"));
        int soLuong = Integer.parseInt(request.getParameter("soLuong"));
        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
        gioHangChiTiet.setGioHang(gioHang);
        ChiTietSp chiTietSp = gioHangRepository.getChiTietSp(idChiTietSp);
        gioHangChiTiet.setChiTietSp(chiTietSp);
        BigDecimal giaBan = chiTietSp.getGiaBan();
        BigDecimal soLuongTon = BigDecimal.valueOf(chiTietSp.getSoLuongTon());
        BigDecimal donGia = giaBan.multiply(soLuongTon);
        gioHangChiTiet.setDonGia(donGia);
        gioHangChiTiet.setSoLuongTon(soLuong);
        boolean result = gioHangRepository.insertGioHangChiTiet(gioHangChiTiet);
        if (result) {
            response.sendRedirect(request.getContextPath() + "/GioHangUserServlet/index");
        } else {
            // Xử lý lỗi
        }
    }


    protected void updateGioHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("user");
        UUID idGioHangChiTiet = UUID.fromString(request.getParameter("id"));
        int soLuong = Integer.parseInt(request.getParameter("soLuong"));
        GioHangChiTiet gioHangChiTiet = gioHangRepository.getGioHangChiTietById(idGioHangChiTiet);
        if (gioHangChiTiet != null) {
            gioHangChiTiet.setSoLuongTon(soLuong);
            gioHangRepository.updateGioHangChiTiet(gioHangChiTiet);
        }
        response.sendRedirect("/Assignment_Sof3011_war_exploded/GioHangUserServlet/index");
    }
    protected void deleteGioHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("user");
        UUID id = UUID.fromString(request.getParameter("id"));
        UUID idGioHang = gioHangRepository.getGioHang(khachHang.getId()).getId();
        boolean result = gioHangRepository.delete(idGioHang, id);
        if (result) {
            response.sendRedirect(request.getContextPath() + "/GioHangUserServlet/index");
        } else {
            // Xử lý lỗi
        }
    }

}