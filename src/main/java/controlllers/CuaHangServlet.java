package controlllers;

import entitis.CuaHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import services.CuaHangService;
import services.impl.CuaHangServiceImpl;
import viewModel.CuaHangViewModel;

import java.io.IOException;
import java.util.List;


@WebServlet({
        "/cua-hang/index",    // GET
        "/cua-hang/create",   // GET
        "/cua-hang/edit",     // GET
        "/cua-hang/delete",   // GET
        "/cua-hang/store",    // POST
        "/cua-hang/update",   // POST
})

public class CuaHangServlet extends HttpServlet {
    private CuaHangService cuahangService = new CuaHangServiceImpl();
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
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
            CuaHang cuaHang = new CuaHang();
            BeanUtils.populate(cuaHang, request.getParameterMap());
            cuahangService.them(cuaHang);
        } catch (Exception e) {
            e.printStackTrace();
        }
       response.sendRedirect("/Assignment_Sof3011_war_exploded/cua-hang/index");

    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/cuaHang/create.jsp")
                .forward(request, response);
    }
    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<CuaHang> list = cuahangService.getList();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/views/cuaHang/index.jsp")
                .forward(request, response);
    }
}
