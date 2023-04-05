package filter;

import entities.KhachHang;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebFilter({
        "/ChiTietSanPhamUserServlet",
        "/GioHangUserServlet/*",
        "/HoaDonUserServlet/*",
})
public class UserFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("user");
        if (khachHang == null) {
            resp.sendRedirect(req.getContextPath() + "/LoginServlet/login");
        } else {
            chain.doFilter(request, response);
        }
    }
}
