package controlllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;
import view_model.QLSP;

import java.io.IOException;

@WebServlet({
        "/san-pham/index",    // GET
        "/san-pham/create",   // GET
        "/san-pham/edit",     // GET
        "/san-pham/delete",   // GET
        "/san-pham/store",    // POST
        "/san-pham/update",   // POST
})
public class SanPhamServlet extends HttpServlet {
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        this.create(request, response);
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
        request.getRequestDispatcher("/views/sanPham/create.jsp").forward(request, response);
    }
    protected void store(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        try {
            QLSP qlsp = new QLSP();
            BeanUtils.populate(qlsp, request.getParameterMap());
                request.setAttribute("qlsp", qlsp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/sanPham/index.jsp").forward(request, response);
    }
}
