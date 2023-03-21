package controlllers.user;

import entities.KhachHang;
import entities.NhanVien;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repositories.ChiTietSanPhamRepository;
import repositories.KhachHangRepository;
import repositories.NhanVienRepository;

import java.io.IOException;

@WebServlet({"/LoginServlet/login", "/LoginServlet/register", "/LoginServlet/forgot-password"})
public class LoginServlet extends HttpServlet {
    private KhachHangRepository khachHangRepository = new KhachHangRepository();
    private NhanVienRepository nhanVienRepository = new NhanVienRepository();
    private ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("login")) {
            login_get(request, response);
        } else if (uri.contains("register")) {
            register_get(request, response);
        } else if (uri.contains("forgot-password")) {
            forgotPassword_get(request, response);
        }
    }

    protected void register_get(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        request.getRequestDispatcher("/views/user/formDangNhap/login.jsp").forward(request, response);
    }

    protected void login_get(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        request.getRequestDispatcher("/views/user/formDangNhap/login.jsp").forward(request, response);
    }

    protected void forgotPassword_get(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        request.getRequestDispatcher("/views/user/formDangNhap/forgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("login")) {
            login_post(request, response);
        } else if (uri.contains("register")) {
//                register_post(request, response);
        } else if (uri.contains("forgot-password")) {
//                forgotPassword_post(request, response);
        }
    }

    protected void login_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String matKhau = request.getParameter("matKhau");
        KhachHang khachHang = khachHangRepository.checkLogin(email, matKhau);
        if (khachHang != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", khachHang);
            request.setAttribute("sanPham", chiTietSanPhamRepository.getList());
            request.setAttribute("view", "/views/user/sanPham/sanPham.jsp");
            response.sendRedirect(request.getContextPath() + "/SanPhamUserServlet");
        } else {
            request.setAttribute("error", "Email hoặc mật khẩu không đúng!");
            request.getRequestDispatcher("/views/user/formDangNhap/login.jsp").forward(request, response);
        }
    }


}
