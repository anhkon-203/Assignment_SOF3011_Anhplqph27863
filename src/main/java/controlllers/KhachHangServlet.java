package controlllers;

import entitis.KhachHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import services.KhachHangService;
import services.impl.KhachHangServiceImpl;
import viewModel.KhachHangViewModel;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@WebServlet({
        "/khach-hang/index",    // GET
        "/khach-hang/create",   // GET
        "/khach-hang/edit",     // GET
        "/khach-hang/delete",   // GET
        "/khach-hang/store",    // POST
        "/khach-hang/update",   // POST
})

public class KhachHangServlet extends HttpServlet {
    private KhachHangService khachHangService = new KhachHangServiceImpl();

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
            DateTimeConverter dtc = new DateConverter(new Date());
            dtc.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dtc, Date.class);
            KhachHang khachHang = new KhachHang();
            BeanUtils.populate(khachHang, request.getParameterMap());
            khachHangService.them(khachHang);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Assignment_Sof3011_war_exploded/khach-hang/index");

    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/khachHang/create.jsp")
                .forward(request, response);
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<KhachHang> listKhachHang = khachHangService.getList();
        request.setAttribute("listKhachHang", listKhachHang);
        request.getRequestDispatcher("/views/khachHang/index.jsp")
                .forward(request, response);
    }
}
