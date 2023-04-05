package filter;

import entities.KhachHang;
import entities.NhanVien;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({
        "/san-pham/*",
        "/nsx/*",
        "/nhan-vien/*",
        "/mau-sac/*",
        "/khach-hang/*",
        "/hoa-don/*",
        "/dong-san-pham/*",
        "/cua-hang/*",
        "/chuc-vu/*",
        "/chi-tiet-san-pham/*",
})
public class AdminFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        NhanVien nhanVien = (NhanVien) session.getAttribute("admin");
        if (nhanVien == null) {
            resp.sendRedirect(req.getContextPath() + "/LoginAdminServlet/login");
        } else {
            chain.doFilter(request, response);
        }
    }

}
