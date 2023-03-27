package controlllers.admin;

import entities.NSX;
import entities.SanPham;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.NSXRepository;


import java.io.IOException;
import java.util.List;

@WebServlet({
        "/nsx/index",    // GET
        "/nsx/create",   // GET
        "/nsx/edit",     // GET
        "/nsx/delete",   // GET
        "/nsx/store",    // POST
        "/nsx/update",   // POST
})
public class NSXServlet extends HttpServlet {

    private NSXRepository nsxRepository = new NSXRepository();

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
        request.setAttribute("view_nSX", "/views/admin/nSX/create.jsp");
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
                response.sendRedirect(request.getContextPath() + "/nsx/create");
                return;
            }
            NSX nsx = new NSX();
            BeanUtils.populate(nsx, request.getParameterMap());
            if (nsxRepository.findByMa(ma) != null) {
                request.getSession().setAttribute("mess_error", "Mã sản phẩm đã tồn tại");
                response.sendRedirect(request.getContextPath() + "/nsx/create");
                return;
            }
            if (nsxRepository.insert(nsx)) {
                request.getSession().setAttribute("message", "Thêm mới thành công");
                response.sendRedirect("/Assignment_Sof3011_war_exploded/nsx/index");
            } else {
                request.getSession().setAttribute("mess_error", "Thêm mới thất bại");
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
        List<NSX> list = nsxRepository.getAll();
        request.setAttribute("list", list);
        request.setAttribute("view_nSX", "/views/admin/nSX/index.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }

    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        String ma = request.getParameter("ma");
        NSX nsx = nsxRepository.findByMa(ma);
        nsxRepository.delete(nsx);
        response.sendRedirect("/Assignment_Sof3011_war_exploded/nsx/index");
    }

    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        String ma = request.getParameter("ma");
        NSX nsx = nsxRepository.findByMa(ma);
        request.setAttribute("nsx", nsx);
        request.setAttribute("view_nSX", "/views/admin/nSX/edit.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
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
                response.sendRedirect(request.getContextPath() + "/nsx/edit?ma=" + ma);
                return;
            }
            NSX nsx = new NSX();
            BeanUtils.populate(nsx, request.getParameterMap());
            if (nsxRepository.update(ma,nsx)) {
                request.getSession().setAttribute("message", "Update thành công");
                response.sendRedirect("/Assignment_Sof3011_war_exploded/nsx/index");
            } else {
                request.getSession().setAttribute("mess_error", "Update thất bại");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
