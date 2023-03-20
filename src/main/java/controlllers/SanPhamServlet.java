package controlllers;

import entities.SanPham;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.SanPhamRepository;


import java.io.IOException;
import java.util.List;

@WebServlet({
        "/san-pham/index",    // GET
        "/san-pham/create",   // GET
        "/san-pham/edit",     // GET
        "/san-pham/delete",   // GET
        "/san-pham/store",    // POST
        "/san-pham/update",   // POST
})
public class SanPhamServlet extends HttpServlet {
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();

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


    protected void store(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        try {
            SanPham sp = new SanPham();
            BeanUtils.populate(sp, request.getParameterMap());
            sanPhamRepository.insert(sp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Assignment_Sof3011_war_exploded/san-pham/index");
    }
    protected void create(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        request.getRequestDispatcher("/views/sanPham/create.jsp").forward(request, response);
    }
    protected void index(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        List<SanPham> list = sanPhamRepository.getAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/views/sanPham/index.jsp").forward(request, response);
    }
    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        String ma = request.getParameter("ma");
        SanPham sp = sanPhamRepository.findByMa(ma);
        sanPhamRepository.delete(sp);
        response.sendRedirect("/Assignment_Sof3011_war_exploded/san-pham/index");
    }
    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        String ma = request.getParameter("ma");
        SanPham sp = sanPhamRepository.findByMa(ma);
        request.setAttribute("sp", sp);
        request.getRequestDispatcher("/views/sanPham/edit.jsp").forward(request, response);
    }    protected void update(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        SanPham sp = new SanPham();
        sp.setMa(ma);
        sp.setTen(ten);
        sanPhamRepository.update(ma, sp);
        response.sendRedirect("/Assignment_Sof3011_war_exploded/san-pham/index");
    }
}
