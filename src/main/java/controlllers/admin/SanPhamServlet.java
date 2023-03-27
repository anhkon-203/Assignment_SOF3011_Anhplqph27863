package controlllers.admin;

import entities.SanPham;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.commons.beanutils.BeanUtils;
import repositories.SanPhamRepository;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@MultipartConfig
@WebServlet({
        "/san-pham/index",    // GET
        "/san-pham/create",   // GET
        "/san-pham/edit",     // GET
        "/san-pham/delete",   // GET
        "/san-pham/store",    // POST
        "/san-pham/update",   // POST
})
public class SanPhamServlet extends HttpServlet {
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("create")) {
            create(request, response);
        } else if (uri.contains("edit")) {
            edit(request, response);
        } else if (uri.contains("delete")) {
            delete(request, response);
        } else {
            this.index(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            update(request, response);
        }
    }


    protected void store(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            Part part = request.getPart("srcImage");
            String ten = request.getParameter("ten");
            String realPath = request.getServletContext().getRealPath("/images");
            String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
            if (ma.trim().isEmpty() || ten.trim().isEmpty()) {
                request.getSession().setAttribute("mess_error", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/san-pham/create");
                return;
            }
            if (fileName.isEmpty()) {
                request.getSession().setAttribute("mess_error", "Vui lòng chọn ảnh");
                response.sendRedirect(request.getContextPath() + "/san-pham/create");
                return;
            }

            part.write(realPath + "/" + fileName);
            request.setAttribute("srcImage", fileName);
            SanPham sp = new SanPham();
            sp.setSrcImage(fileName);
            BeanUtils.populate(sp, request.getParameterMap());
            if (sanPhamRepository.findByMa(ma) != null) {
                request.getSession().setAttribute("mess_error", "Mã sản phẩm đã tồn tại");
                response.sendRedirect(request.getContextPath() + "/san-pham/create");
                return;
            }
            if (sanPhamRepository.insert(sp)) {
                request.getSession().setAttribute("message", "Thêm mới thành công");
                response.sendRedirect(request.getContextPath() + "/san-pham/index");
            } else {
                request.getSession().setAttribute("mess_error", "Thêm mới thất bại");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        request.setAttribute("view_sanPham", "/views/admin/sanPham/create.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        List<SanPham> list = sanPhamRepository.getAll();
        String realPath = request.getServletContext().getRealPath("/images");

        // Thay đổi đường dẫn tới ảnh để hiển thị ảnh thay vì đường dẫn
        for (SanPham sp : list) {
            String fileName = sp.getSrcImage();
            if (fileName != null) {
                sp.setSrcImage(request.getContextPath() + "/images/" + fileName);
            }
        }
        request.setAttribute("list", list);
        request.setAttribute("view_sanPham", "/views/admin/sanPham/index.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }

    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        String ma = request.getParameter("ma");
        SanPham sp = sanPhamRepository.findByMa(ma);
        sanPhamRepository.delete(sp);
        response.sendRedirect("/Assignment_Sof3011_war_exploded/san-pham/index");
    }

    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        String ma = request.getParameter("ma");
        SanPham sp = sanPhamRepository.findByMa(ma);
        request.setAttribute("sp", sp);
        request.setAttribute("view_sanPham", "/views/admin/sanPham/edit.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response)
            throws
            ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            Part part = request.getPart("srcImage");
            String ten = request.getParameter("ten");
            String realPath = request.getServletContext().getRealPath("/images");
            String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
            if (ma.trim().isEmpty() || ten.trim().isEmpty()) {
                request.getSession().setAttribute("mess_error", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/san-pham/edit?ma=" + ma);
                return;
            }
            if (fileName.isEmpty()) {
                request.getSession().setAttribute("mess_error", "Vui lòng chọn ảnh");
                response.sendRedirect(request.getContextPath() + "/san-pham/edit?ma=" + ma);
                return;
            }
            part.write(realPath + "/" + fileName);
            request.setAttribute("srcImage", fileName);
            SanPham sp = new SanPham();
            sp.setSrcImage(fileName);
            BeanUtils.populate(sp, request.getParameterMap());
            if (sanPhamRepository.update(ma, sp)) {
                request.getSession().setAttribute("message", "Update thành công");
                response.sendRedirect("/Assignment_Sof3011_war_exploded/san-pham/index");
            } else {
                request.getSession().setAttribute("mess_error", "Update thất bại");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
