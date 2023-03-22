package controlllers.user;

import entities.GioHang;
import entities.KhachHang;
import entities.NhanVien;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import repositories.ChiTietSanPhamRepository;
import repositories.GioHangRepository;
import repositories.KhachHangRepository;
import repositories.NhanVienRepository;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.*;

@WebServlet({"/LoginServlet/login", "/LoginServlet/register", "/LoginServlet/forgot-password", "/LoginServlet/logout"})
public class LoginServlet extends HttpServlet {
    private KhachHangRepository khachHangRepository = new KhachHangRepository();
    private NhanVienRepository nhanVienRepository = new NhanVienRepository();
    private GioHangRepository gioHangRepository = new GioHangRepository();
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
        } else if (uri.contains("logout")) {
            logout(request, response);
        }
    }

    protected void register_get(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        request.getRequestDispatcher("/views/user/formDangNhap/register.jsp").forward(request, response);
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

    protected void logout(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate(); // Thay đổi này để xóa toàn bộ session
        response.sendRedirect(request.getContextPath() + "/SanPhamUserServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("login")) {
            login_post(request, response);
        } else if (uri.contains("register")) {
            register(request, response);
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

    protected void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Random random = new Random();
            DateTimeConverter dtc = new DateConverter(new Date());
            dtc.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dtc, Date.class);
            KhachHang khachHang = new KhachHang();
            khachHang.setMa("KH" + random.nextInt(1000000));
            BeanUtils.populate(khachHang, request.getParameterMap());
            khachHangRepository.insert(khachHang);


            GioHang gioHang = new GioHang();
            gioHang.setMa(khachHang.getMa());
            gioHang.setKhachHang(khachHang);
            int year = LocalDate.now().getYear();
            int month = LocalDate.now().getMonthValue();
            int day = LocalDate.now().getDayOfMonth();
            gioHang.setNgayTao(java.sql.Date.valueOf(LocalDate.of(year, month, day)));
            gioHang.setTrangThai(0);
            gioHangRepository.insertGioHang(gioHang);
            response.sendRedirect(request.getContextPath() + "/LoginServlet/login");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

