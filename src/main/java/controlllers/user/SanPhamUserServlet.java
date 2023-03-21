package controlllers.user;

import entities.KhachHang;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repositories.ChiTietSanPhamRepository;
import repositories.KhachHangRepository;
import repositories.SanPhamRepository;

import java.io.IOException;

@WebServlet(value = "/SanPhamUserServlet")
public class SanPhamUserServlet extends HttpServlet {

    private ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.index(request, response);
    }
    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("user");
        // setime out
        session.setMaxInactiveInterval(60 * 2);
//        if (khachHang == null) {
//            // Nếu chưa đăng nhập, yêu cầu người dùng đăng nhập
//            request.getRequestDispatcher("/views/user/formDangNhap/login.jsp").forward(request, response);
//            return;
//        }
        request.setAttribute("sanPham", chiTietSanPhamRepository.getList());
        request.setAttribute("view", "/views/user/sanPham/sanPham.jsp");
        request.getRequestDispatcher("/views/user/layoutUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
