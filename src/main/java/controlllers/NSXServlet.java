package controlllers;

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
        request.getRequestDispatcher("/views/nSX/create.jsp").forward(request, response);
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        try {
            NSX nsx = new NSX();
            BeanUtils.populate(nsx, request.getParameterMap());
            nsxRepository.insert(nsx);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Assignment_Sof3011_war_exploded/nsx/index");
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        List<NSX> list = nsxRepository.getAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/views/nSX/index.jsp").forward(request, response);
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
        request.getRequestDispatcher("/views/nSX/edit.jsp").forward(request, response);
    }
    protected void update(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        NSX nsx = new NSX();
        nsx.setMa(ma);
        nsx.setTen(ten);
        nsxRepository.update(ma, nsx);
        response.sendRedirect("/Assignment_Sof3011_war_exploded/nsx/index");
    }
}
