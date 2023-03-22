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
        String idChiTietSp = request.getParameter("id");
        request.getParameter("soLuong");
        int soLuong = Integer.parseInt(request.getParameter("soLuong"));
        List<GioHangChiTiet> list = gioHangRepository.getGioHangChiTietByIdGioHang(gioHang.getId(), idChiTietSp);
        if (list.isEmpty()) {
            GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setGioHang(gioHang);
            ChiTietSp chiTietSp = gioHangRepository.getChiTietSp(idChiTietSp);
            gioHangChiTiet.setChiTietSp(chiTietSp);
            gioHangChiTiet.setDonGia((float) (chiTietSp.getGiaBan() * chiTietSp.getSoLuongTon()));
            gioHangChiTiet.setSoLuongTon(soLuong);
            gioHangRepository.insertGioHangChiTiet(gioHangChiTiet);
        } else {
            GioHangChiTiet gioHangChiTiet = list.get(0);
            gioHangChiTiet.setSoLuongTon(gioHangChiTiet.getSoLuongTon() + soLuong);
            gioHangRepository.updateGioHangChiTiet(gioHangChiTiet);
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
        response.sendRedirect("/Assignment_Sof3011_war_exploded/GioHangUserServlet/index");
    }
    protected void deleteGioHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("user");
        String id = request.getParameter("id");
        GioHangChiTiet gioHangChiTiet = gioHangRepository.getGioHangChiTietById(id);
        GioHang gioHang = gioHangRepository.getGioHang(khachHang.getId());
            gioHangRepository.delete(id, gioHang.getId());
            response.sendRedirect("/Assignment_Sof3011_war_exploded/GioHangUserServlet/index");

    }

}