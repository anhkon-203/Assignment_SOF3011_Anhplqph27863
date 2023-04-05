package controlllers.admin;

import entities.KhachHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import repositories.KhachHangRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@WebServlet({
        "/khach-hang/index",    // GET
        "/khach-hang/create",   // GET
        "/khach-hang/edit",     // GET
        "/khach-hang/delete",   // GET
        "/khach-hang/store",    // POST
        "/khach-hang/update",   // POST
})

public class KhachHangServlet extends HttpServlet {
    private KhachHangRepository khachHangRepository = new KhachHangRepository();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String uri = request.getRequestURI();
        if (uri.contains("create")) {
            create(request, response);
        } else if (uri.contains("edit")) {
            edit(request, response);
        } else if (uri.contains("delete")) {
            delete(request, response);
        } else {
            this.index(request, response);
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        }
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            String ten = request.getParameter("ten");
            String tenDem = request.getParameter("tenDem");
            String ho = request.getParameter("ho");
            String sdt = request.getParameter("sdt");
            String email = request.getParameter("email");
            String diaChi = request.getParameter("diaChi");
            String matKhau = request.getParameter("matKhau");

            if (ma.trim().isEmpty() || ten.trim().isEmpty() || tenDem.trim().isEmpty() || ho.trim().isEmpty() || sdt.trim().isEmpty() || email.trim().isEmpty() || diaChi.trim().isEmpty() || matKhau.trim().isEmpty()) {
                request.getSession().setAttribute("mess_error", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/khach-hang/create");
                return;
            }
            if(khachHangRepository.findByMa(ma) != null){
                request.getSession().setAttribute("mess_error", "Mã khách hàng đã tồn tại");
                response.sendRedirect(request.getContextPath() + "khach-hang/create");
                return;
            }
            DateTimeConverter dtc = new DateConverter(new Date());
            dtc.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dtc, Date.class);
            KhachHang khachHang = new KhachHang();
            BeanUtils.populate(khachHang, request.getParameterMap());
            if (khachHangRepository.insert(khachHang)){
                request.getSession().setAttribute("message", "Thêm thành công");
                response.sendRedirect("/Assignment_Sof3011_war_exploded/khach-hang/index");
            }else {
                request.getSession().setAttribute("mess_error", "Thêm thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            String maKH = request.getParameter("ma");
            String ten = request.getParameter("ten");
            String tenDem = request.getParameter("tenDem");
            String ho = request.getParameter("ho");
            String sdt = request.getParameter("sdt");
            String email = request.getParameter("email");
            String diaChi = request.getParameter("diaChi");
            String matKhau = request.getParameter("matKhau");
            if (maKH.trim().isEmpty() || ten.trim().isEmpty() || tenDem.trim().isEmpty() || ho.trim().isEmpty() || sdt.trim().isEmpty() || email.trim().isEmpty() || diaChi.trim().isEmpty() || matKhau.trim().isEmpty()) {
                request.getSession().setAttribute("mess_error", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/khach-hang/edit?ma=" + maKH);
                return;
            }

            DateTimeConverter dtc = new DateConverter(new Date());
            dtc.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dtc, Date.class);
            KhachHang khachHang = khachHangRepository.findByMa(maKH);
            BeanUtils.populate(khachHang, request.getParameterMap());
            if (khachHangRepository.update(khachHang)){
                request.getSession().setAttribute("message", "Cập nhật thành công");
                response.sendRedirect("/Assignment_Sof3011_war_exploded/khach-hang/index");
            }else {
                request.getSession().setAttribute("mess_error", "Cập nhật thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("view_khachHang", "/views/admin/khachHang/create.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<KhachHang> listKhachHang = khachHangRepository.getAll();
        request.setAttribute("listKhachHang", listKhachHang);
        request.setAttribute("view_khachHang", "/views/admin/khachHang/index.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }

    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        KhachHang khachHang = khachHangRepository.findByMa(ma);
        khachHangRepository.delete(khachHang);
        response.sendRedirect("/Assignment_Sof3011_war_exploded/khach-hang/index");

    }
    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        KhachHang khachHang = khachHangRepository.findByMa(ma);
        request.setAttribute("khachHang", khachHang);
        request.setAttribute("view_khachHang", "/views/admin/khachHang/edit.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }
}
