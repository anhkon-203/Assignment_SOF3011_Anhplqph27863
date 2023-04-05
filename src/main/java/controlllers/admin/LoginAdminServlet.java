package controlllers.admin;

import entities.KhachHang;
import entities.NhanVien;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repositories.NhanVienRepository;

import java.io.IOException;

@WebServlet(value ={"/LoginAdminServlet/login","/LoginAdminServlet/check"} )
public class LoginAdminServlet extends HttpServlet {
    private NhanVienRepository nhanVienRepository = new NhanVienRepository();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.index(request, response);
    }
    protected void index(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        request.getRequestDispatcher("/views/admin/loginAdmin.jsp").forward(request, response);
    }
    protected void check(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        String email = request.getParameter("email");
        String matKhau = request.getParameter("matKhau");
        if ( email.trim().isEmpty()  || matKhau.trim().isEmpty()) {
            request.getSession().setAttribute("error", "Email hoặc mật khẩu không được để trống");
            response.sendRedirect(request.getContextPath() + "/LoginAdminServlet/login");
            return;
        }
        NhanVien nhanVien = nhanVienRepository.checkLoginNV(email, matKhau);
        if (nhanVienRepository.checkLoginNV(email, matKhau) == null) {
            request.getSession().setAttribute("error", "Email hoặc mật khẩu không đúng");
            response.sendRedirect(request.getContextPath() + "/LoginAdminServlet/login");
            return;
        }
        if (nhanVien != null) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", nhanVien);
            response.sendRedirect(request.getContextPath() + "/san-pham/index");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.check(request, response);
    }
}
