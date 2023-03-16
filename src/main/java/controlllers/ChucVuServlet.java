package controlllers;

import entitis.ChucVu;
import entitis.MauSac;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.ChucVuRepository;
import repositories.MauSacRepository;

import java.io.IOException;
import java.util.List;

@WebServlet({
        "/chuc-vu/index",    // GET
        "/chuc-vu/create",   // GET
        "/chuc-vu/edit",     // GET
        "/chuc-vu/delete",   // GET
        "/chuc-vu/store",    // POST
        "/chuc-vu/update",   // POST
})
public class ChucVuServlet extends HttpServlet {
    private ChucVuRepository chucVuRepository = new ChucVuRepository();

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
            edit(request, response);
        } else if (url.contains("delete")) {
            delete(request, response);
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
        request.getRequestDispatcher("/views/chucVu/create.jsp").forward(request, response);
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        try {
            ChucVu chucVu = new ChucVu();
            BeanUtils.populate(chucVu, request.getParameterMap());
            chucVuRepository.insert(chucVu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Assignment_Sof3011_war_exploded/chuc-vu/index");
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        List<ChucVu> list = chucVuRepository.getAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/views/chucVu/index.jsp").forward(request, response);
    }

    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        String ma = request.getParameter("ma");
        ChucVu chucVu = chucVuRepository.findByMa(ma);
        chucVuRepository.delete(chucVu);
        response.sendRedirect("/Assignment_Sof3011_war_exploded/chuc-vu/index");
    }
    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        String ma = request.getParameter("ma");
        ChucVu chucVu = chucVuRepository.findByMa(ma);
        request.setAttribute("chucVu", chucVu);
        request.getRequestDispatcher("/views/chucVu/edit.jsp").forward(request, response);
    }
}
