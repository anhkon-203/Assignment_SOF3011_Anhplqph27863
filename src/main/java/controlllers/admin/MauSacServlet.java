package controlllers.admin;

import entities.MauSac;
import entities.NSX;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.MauSacRepository;


import java.io.IOException;
import java.util.List;

@WebServlet({
        "/mau-sac/index",    // GET
        "/mau-sac/create",   // GET
        "/mau-sac/edit",     // GET
        "/mau-sac/delete",   // GET
        "/mau-sac/store",    // POST
        "/mau-sac/update",   // POST
})
public class MauSacServlet extends HttpServlet {
    private MauSacRepository mauSacRepository = new MauSacRepository();

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
        request.setAttribute("view_mauSac", "/views/admin/mauSac/create.jsp");
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
                response.sendRedirect(request.getContextPath() + "/mau-sac/create");
                return;
            }
            MauSac mauSac = new MauSac();
            BeanUtils.populate(mauSac, request.getParameterMap());
            if (mauSacRepository.findByMa(ma) != null) {
                request.getSession().setAttribute("mess_error", "Mã đã tồn tại");
                response.sendRedirect(request.getContextPath() + "/mau-sac/create");
                return;
            }
            if (mauSacRepository.insert(mauSac)) {
                request.getSession().setAttribute("message", "Thêm mới thành công");
                response.sendRedirect(request.getContextPath() + "/mau-sac/index");
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
                response.sendRedirect(request.getContextPath() + "/mau-sac/edit?ma=" + ma);
                return;
            }
            MauSac mauSac = new MauSac();
            BeanUtils.populate(mauSac, request.getParameterMap());
            if (mauSacRepository.update(ma,mauSac)) {
                request.getSession().setAttribute("message", "Update thành công");
                response.sendRedirect(request.getContextPath() + "/mau-sac/index");
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
        List<MauSac> list = mauSacRepository.getAll();
        request.setAttribute("list", list);
        request.setAttribute("view_mauSac", "/views/admin/mauSac/index.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }

    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        String ma = request.getParameter("ma");
        MauSac mauSac = mauSacRepository.findByMa(ma);
        mauSacRepository.delete(mauSac);
        response.sendRedirect("/Assignment_Sof3011_war_exploded/mau-sac/index");
    }

    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        String ma = request.getParameter("ma");
        MauSac mauSac = mauSacRepository.findByMa(ma);
        request.setAttribute("mauSac", mauSac);
        request.setAttribute("view_mauSac", "/views/admin/mauSac/edit.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }
}
