package controlllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import view_model.QLDSP;

import java.io.IOException;

@WebServlet({
        "/dong-san-pham/index",    // GET
        "/dong-san-pham/create",   // GET
        "/dong-san-pham/edit",     // GET
        "/dong-san-pham/delete",   // GET
        "/dong-san-pham/store",    // POST
        "/dong-san-pham/update",   // POST
})
public class DongSanPhamServlet extends HttpServlet {
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
        request.getRequestDispatcher("/views/dongSp/create.jsp").forward(request, response);
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        try {
            QLDSP qldsp = new QLDSP();
            BeanUtils.populate(qldsp, request.getParameterMap());
            request.setAttribute("qldsp", qldsp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/dongSp/index.jsp").forward(request, response);
    }
}
