package controlllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import view_model.QLCH;

import java.io.IOException;


@WebServlet({
        "/cua-hang/index",    // GET
        "/cua-hang/create",   // GET
        "/cua-hang/edit",     // GET
        "/cua-hang/delete",   // GET
        "/cua-hang/store",    // POST
        "/cua-hang/update",   // POST
})

public class CuaHangServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        this.create(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        this.store(request, response);
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            QLCH qlch = new QLCH();
            BeanUtils.populate(qlch, request.getParameterMap());
            request.setAttribute("qlch", qlch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/cuaHang/index.jsp")
                .forward(request, response);

    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/cuaHang/create.jsp")
                .forward(request, response);
    }
}
