package controlllers.admin;

import entities.DongSp;
import entities.MauSac;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.DongSpRepository;


import java.io.IOException;
import java.util.List;

@WebServlet({
        "/dong-san-pham/index",    // GET
        "/dong-san-pham/create",   // GET
        "/dong-san-pham/edit",     // GET
        "/dong-san-pham/delete",   // GET
        "/dong-san-pham/store",    // POST
        "/dong-san-pham/update",   // POST
})
public class DongSanPhamServlet extends HttpServlet {
    private DongSpRepository dongSpRepository = new DongSpRepository();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            update(request, response);
        }
    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        request.setAttribute("view_dongSP", "/views/admin/dongSp/create.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            String ten = request.getParameter("ten");
            if (ma.trim().isEmpty() || ten.trim().isEmpty()) {
                request.getSession().setAttribute("mess_error", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() +"/dong-san-pham/create");
                return;
            }
            DongSp dongSp = new DongSp();
            BeanUtils.populate(dongSp, request.getParameterMap());
            if (dongSpRepository.findByMa(ma) != null) {
                request.getSession().setAttribute("mess_error", "Mã đã tồn tại");
                response.sendRedirect(request.getContextPath() +"/dong-san-pham/create");
                return;
            }
            if (dongSpRepository.insert(dongSp)) {
                request.getSession().setAttribute("message", "Thêm mới thành công");
                response.sendRedirect(request.getContextPath() +"/dong-san-pham/index");
            } else {
                request.getSession().setAttribute("mess_error", "Thêm mới thất bại");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            String ten = request.getParameter("ten");
            if (ma.trim().isEmpty() || ten.trim().isEmpty()) {
                request.getSession().setAttribute("mess_error", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/dong-san-pham/edit?ma=" + ma);
                return;
            }
            DongSp dongSp = new DongSp();
            BeanUtils.populate(dongSp, request.getParameterMap());
            if (dongSpRepository.update(ma,dongSp)) {
                request.getSession().setAttribute("message", "Update thành công");
                response.sendRedirect(request.getContextPath() +"/dong-san-pham/index");
            } else {
                request.getSession().setAttribute("mess_error", "Update thất bại");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        List<DongSp> list = dongSpRepository.getAll();
        request.setAttribute("list", list);
        request.setAttribute("view_dongSP", "/views/admin/dongSp/index.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }
    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
            String ma = request.getParameter("ma");
            DongSp dongSp = this.dongSpRepository.findByMa(ma);
            dongSpRepository.delete(dongSp);
        response.sendRedirect("/Assignment_Sof3011_war_exploded/dong-san-pham/index");
    }
    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        String ma = request.getParameter("ma");
        DongSp dongSp = this.dongSpRepository.findByMa(ma);
        request.setAttribute("dongSp",dongSp);
        request.setAttribute("view_dongSP", "/views/admin/dongSp/edit.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }
}
