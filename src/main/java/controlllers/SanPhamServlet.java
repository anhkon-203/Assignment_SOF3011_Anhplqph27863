package controlllers;

import entitis.SanPham;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import services.SanPhamService;
import services.impl.SanPhamServiceImpl;
import viewModel.SanPhamViewModel;


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
    private SanPhamService sanPhamService = new SanPhamServiceImpl();

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
//            edit(request, response);
        } else if (url.contains("delete")) {
//            delete(request, response);
        } else {
            this.index(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.store(request, response);
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        try {
            SanPham sp = new SanPham();
            BeanUtils.populate(sp, request.getParameterMap());
            sanPhamService.them(sp);
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
        List<SanPham> list = sanPhamService.getList();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/views/sanPham/index.jsp").forward(request, response);
    }
}
