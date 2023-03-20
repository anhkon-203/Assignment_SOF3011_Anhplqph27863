package controlllers;

import entities.DongSp;
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
        request.setAttribute("view_dongSP", "/views/dongSp/create.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        try {
            DongSp dongSp = new DongSp();
            BeanUtils.populate(dongSp, request.getParameterMap());
            dongSpRepository.insert(dongSp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Assignment_Sof3011_war_exploded/dong-san-pham/index");
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            DongSp dongSp = new DongSp();
            BeanUtils.populate(dongSp, request.getParameterMap());
            dongSpRepository.update(ma,dongSp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Assignment_Sof3011_war_exploded/dong-san-pham/index");
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        List<DongSp> list = dongSpRepository.getAll();
        request.setAttribute("list", list);
        request.setAttribute("view_dongSP", "/views/dongSp/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
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
        request.setAttribute("view_dongSP", "/views/dongSp/edit.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }
}
