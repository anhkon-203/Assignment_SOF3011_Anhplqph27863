package controlllers.user;

import entities.ChiTietSp;
import entities.GioHang;
import entities.GioHangChiTiet;
import entities.KhachHang;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repositories.GioHangRepository;
import viewModel.ChiTietSanPhamViewModel;
import viewModel.GioHangChiTietViewModel;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/GioHangUserServlet/*")
public class GioHangUserServlet extends HttpServlet {
    private GioHangRepository gioHangRepository = new GioHangRepository();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null) {
            path = "/index";
        }
        switch (path) {
            case "/index":
            case "/create":
            case "/edit":
                showGioHang(request, response);
                break;
            case "/delete":
                deleteGioHang(request, response);
                break;
            default:
                showGioHang(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/store":
                storeGioHang(request, response);
                break;
            case "/update":
                updateGioHang(request, response);
                break;
            default:
                showGioHang(request, response);
                break;
        }
    }

    protected void showGioHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("user");
        GioHang gioHang = gioHangRepository.getGioHang(khachHang.getId());
        if (gioHang == null) {
            gioHang = new GioHang();
            gioHang.setKhachHang(khachHang);
            int year = LocalDate.now().getYear();
            int month = LocalDate.now().getMonthValue();
            int day = LocalDate.now().getDayOfMonth();
            gioHang.setNgayTao(Date.valueOf(LocalDate.of(year, month, day)));
            gioHang.setTrangThai(0);
            gioHangRepository.insertGioHang(gioHang);
        }
        List<GioHangChiTietViewModel> listGioHangChiTiet = gioHangRepository.getAllGioHangChiTietByGioHangId(gioHang.getId());

        String realPath = request.getServletContext().getRealPath("/images");
        // Thay đổi đường dẫn tới ảnh để hiển thị ảnh thay vì đường dẫn

        for (GioHangChiTietViewModel gioHangChiTietViewModel : listGioHangChiTiet) {
            String fileName = gioHangChiTietViewModel.getSrcImage();
            if (fileName != null) {
                gioHangChiTietViewModel.setSrcImage(request.getContextPath() + "/images/" + fileName);
            }
        }
        request.setAttribute("gioHang", gioHang);
        request.setAttribute("listGioHangChiTiet", listGioHangChiTiet);
        request.setAttribute("view", "/views/user/gioHang/gioHang.jsp");
        request.getRequestDispatcher("/views/user/layoutUser.jsp").forward(request, response);
    }


    protected void storeGioHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("user");
        GioHang gioHang = gioHangRepository.getGioHang(khachHang.getId());
        if (gioHang == null) {
            gioHang = new GioHang();
            gioHang.setKhachHang(khachHang);
            int year = LocalDate.now().getYear();
            int month = LocalDate.now().getMonthValue();
            int day = LocalDate.now().getDayOfMonth();
            gioHang.setNgayTao(Date.valueOf(LocalDate.of(year, month, day)));
            gioHang.setTrangThai(0);
            gioHangRepository.insertGioHang(gioHang);
        }
        String idChiTietSp = request.getParameter("id");
        int soLuong = Integer.parseInt(request.getParameter("soLuong"));
        GioHangChiTiet gioHangChiTiet = gioHangRepository.getGioHangChiTietByIdGioHang(gioHang.getId(), idChiTietSp);
        if (gioHangChiTiet == null) {
            gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setGioHang(gioHang);
            ChiTietSp chiTietSp = gioHangRepository.getChiTietSp(idChiTietSp);
            gioHangChiTiet.setChiTietSp(chiTietSp);
            gioHangChiTiet.setDonGia((float) (chiTietSp.getGiaBan() * chiTietSp.getSoLuongTon()));
            gioHangChiTiet.setSoLuongTon(soLuong);
            gioHangRepository.insertGioHangChiTiet(gioHangChiTiet);
        } else {
            gioHangChiTiet.setSoLuongTon(gioHangChiTiet.getSoLuongTon() + soLuong);
            gioHangRepository.updateGioHangChiTiet(gioHangChiTiet);
        }
        response.sendRedirect(request.getContextPath() + "/GioHangUserServlet/index");
    }

    protected void deleteGioHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("user");
        String idGioHangChiTiet = request.getParameter("id");
        GioHangChiTiet gioHangChiTiet = gioHangRepository.getGioHangChiTietById(idGioHangChiTiet);
        if (gioHangChiTiet != null) {
            gioHangRepository.deltete(gioHangChiTiet);
        }
        response.sendRedirect(request.getContextPath() + "/GioHangUserServlet/index");
    }

    protected void updateGioHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("user");
        String idGioHangChiTiet = request.getParameter("id");
        int soLuong = Integer.parseInt(request.getParameter("soLuong"));
        GioHangChiTiet gioHangChiTiet = gioHangRepository.getGioHangChiTietById(idGioHangChiTiet);
        if (gioHangChiTiet != null) {
            gioHangChiTiet.setSoLuongTon(soLuong);
            gioHangRepository.updateGioHangChiTiet(gioHangChiTiet);
        }
        response.sendRedirect(request.getContextPath() + "/GioHangUserServlet/index");
    }

}