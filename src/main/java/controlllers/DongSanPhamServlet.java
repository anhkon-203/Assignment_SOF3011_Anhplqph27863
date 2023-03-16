package controlllers;

import entitis.DongSp;
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

        String url = request.getRequestURI();
        if (url.contains("create")) {
            create(request, response);
        } else if (url.contains("edit")) {
            edit(request, response);
        } else if (url.contains("delete")) {
            delete(request, response);
        } else {
            this.index(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.store(request, response);
    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        request.getRequestDispatcher("/views/dongSp/create.jsp").forward(request, response);
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

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        List<DongSp> list = dongSpRepository.getAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/views/dongSp/index.jsp").forward(request, response);
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
        request.getRequestDispatcher("/views/dongSp/edit.jsp").forward(request, response);
    }
}
