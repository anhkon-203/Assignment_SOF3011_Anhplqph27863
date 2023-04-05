package controlllers.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repositories.HoaDonRepository;

import java.io.IOException;

@WebServlet(value ={"/hoa-don/index","/hoa-don/update","/hoa-don/detail"})
public class HoaDonServlet extends HttpServlet {
    private HoaDonRepository hoaDonRepository = new HoaDonRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String URI = request.getRequestURI();
        if (URI.contains("index")) {
            index(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().contains("update")) {
            update(request, response);
        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listHoaDon", hoaDonRepository.findAll());
        request.setAttribute("view_hoaDon", "/views/admin/hoaDon/index.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maHD = request.getParameter("maHD");
        if (hoaDonRepository.updateHoaDonNgayShip(maHD,1)){
            response.sendRedirect("/Assignment_Sof3011_war_exploded/hoa-don/index");
        }

    }
}
