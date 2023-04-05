package controlllers.admin;

import entities.CuaHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.CuaHangRepository;


import java.io.IOException;
import java.util.List;


@WebServlet({
        "/cua-hang/index",    // GET
        "/cua-hang/create",   // GET
        "/cua-hang/edit",     // GET
        "/cua-hang/delete",   // GET
        "/cua-hang/store",    // POST
        "/cua-hang/update",   // POST
})

public class CuaHangServlet extends HttpServlet {
    private CuaHangRepository cuaHangRepository = new CuaHangRepository();

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
            update(request, response);
        }
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            String ten = request.getParameter("ten");
            String diaChi = request.getParameter("diaChi");
            if (ma.trim().isEmpty() || ten.trim().isEmpty() || diaChi.trim().isEmpty()) {
                request.getSession().setAttribute("mess_error", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/cua-hang/create");
                return;
            }
            if (cuaHangRepository.findByMa(ma) != null) {
                request.getSession().setAttribute("mess_error", "Mã đã tồn tại");
                response.sendRedirect(request.getContextPath() + "/cua-hang/create");
                return;
            }



            CuaHang cuaHang = new CuaHang();
            BeanUtils.populate(cuaHang, request.getParameterMap());
            if (cuaHangRepository.insert(cuaHang)){
                request.getSession().setAttribute("message", "Thêm thành công");
                response.sendRedirect("/Assignment_Sof3011_war_exploded/cua-hang/index");
            } else {
                request.getSession().setAttribute("mess_error", "Thêm thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            String ten = request.getParameter("ten");
            String diaChi = request.getParameter("diaChi");
            if (ma.trim().isEmpty() || ten.trim().isEmpty() || diaChi.trim().isEmpty()) {
                request.getSession().setAttribute("mess_error", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/cua-hang/create");
                return;
            }
            CuaHang cuaHang = cuaHangRepository.findByMa(ma);
            BeanUtils.populate(cuaHang, request.getParameterMap());
            if (cuaHangRepository.update(cuaHang)){
                request.getSession().setAttribute("message", "Cập nhật thành công");
                response.sendRedirect(request.getContextPath() + "/cua-hang/edit?ma=" + ma);
            } else {
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
        request.setAttribute("view_cuaHang", "/views/admin/cuaHang/create.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<CuaHang> list = cuaHangRepository.getAll();
        request.setAttribute("list", list);
        request.setAttribute("view_cuaHang", "/views/admin/cuaHang/index.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }

    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            CuaHang cuaHang = cuaHangRepository.findByMa(ma);
            cuaHangRepository.delete(cuaHang);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Assignment_Sof3011_war_exploded/cua-hang/index");

    }

    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        CuaHang cuaHang = cuaHangRepository.findByMa(ma);
        request.setAttribute("cuaHang", cuaHang);
        request.setAttribute("view_cuaHang", "/views/admin/cuaHang/edit.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }
}
