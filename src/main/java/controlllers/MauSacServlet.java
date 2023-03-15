package controlllers;

import entitis.MauSac;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import services.MauSacService;
import services.impl.MauSacServiceImpl;
import viewModel.MauSacViewModel;

import java.io.IOException;
import java.util.List;

@WebServlet({
        "/mau-sac/index",    // GET
        "/mau-sac/create",   // GET
        "/mau-sac/edit",     // GET
        "/mau-sac/delete",   // GET
        "/mau-sac/store",    // POST
        "/mau-sac/update",   // POST
})
public class MauSacServlet extends HttpServlet {
    private MauSacService mauSacService = new MauSacServiceImpl();

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

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        request.getRequestDispatcher("/views/mauSac/create.jsp").forward(request, response);
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        try {
            MauSac mauSac = new MauSac();
            BeanUtils.populate(mauSac, request.getParameterMap());
            mauSacService.them(mauSac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Assignment_Sof3011_war_exploded/mau-sac/index");
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        List<MauSac> list = mauSacService.getList();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/views/mauSac/index.jsp").forward(request, response);
    }
}
