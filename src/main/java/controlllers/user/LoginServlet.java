package controlllers.user;

import entities.KhachHang;
import entities.NhanVien;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repositories.KhachHangRepository;
import repositories.NhanVienRepository;

import java.io.IOException;

@WebServlet({"/LoginServlet/login", "/LoginServlet/register", "/LoginServlet/forgot-password"})
public class LoginServlet extends HttpServlet {
    private KhachHangRepository khachHangRepository = new KhachHangRepository();
    private NhanVienRepository nhanVienRepository = new NhanVienRepository();

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

    protected void login_post(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        String email = request.getParameter("email");
        String matKhau = request.getParameter("matKhau");
        KhachHang khachHang = khachHangRepository.checkLogin(email, matKhau);
        if (khachHang != null) {
            request.setAttribute("user", khachHang);
            request.getRequestDispatcher("/views/user/layoutUser.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/user/formDangNhap/login.jsp").forward(request, response);
        }


    }
}
