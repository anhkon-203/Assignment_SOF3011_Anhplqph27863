package controlllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import view_model.QLNSX;

import java.io.IOException;

@WebServlet({
        "/nsx/index",    // GET
        "/nsx/create",   // GET
        "/nsx/edit",     // GET
        "/nsx/delete",   // GET
        "/nsx/store",    // POST
        "/nsx/update",   // POST
})
public class NSXServlet extends HttpServlet {
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
        request.getRequestDispatcher("/views/nSX/create.jsp").forward(request, response);
    }
    protected void store(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        try {
            QLNSX qlnsx = new QLNSX();
            BeanUtils.populate(qlnsx, request.getParameterMap());
            request.setAttribute("qlnsx", qlnsx);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/nSX/index.jsp").forward(request, response);
    }
}
