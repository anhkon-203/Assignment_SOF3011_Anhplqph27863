package controlllers.admin;

import entities.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.*;
import viewModel.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;


@WebServlet({
        "/chi-tiet-san-pham/index",    // GET
        "/chi-tiet-san-pham/create",   // GET
        "/chi-tiet-san-pham/edit",     // GET
        "/chi-tiet-san-pham/delete",   // GET
        "/chi-tiet-san-pham/store",    // POST
        "/chi-tiet-san-pham/update",   // POST
})

public class ChiTietSanPhamServlet extends HttpServlet {
    private ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
    private DongSpRepository dongSpRepository = new DongSpRepository();
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    private MauSacRepository mauSacRepository = new MauSacRepository();
    private NSXRepository nsxRepository = new NSXRepository();
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
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
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            store(request, response);
        } else if (uri.contains("update")) {
            update(request, response);
        }
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {

            // Lấy các giá trị từ form
            UUID idDong = UUID.fromString(request.getParameter("idDong"));
            UUID idNSX = UUID.fromString(request.getParameter("idNSX"));
            UUID idMauSac = UUID.fromString(request.getParameter("idMauSac"));
            UUID idSp = UUID.fromString(request.getParameter("idSp"));
            ChiTietSp chiTietSp = new ChiTietSp();
            // Tạo đối tượng
            DongSp dongSp = new DongSp();
            dongSp.setId(idDong);
            NSX nsx = new NSX();
            nsx.setId(idNSX);
            MauSac mauSac = new MauSac();
            mauSac.setId(idMauSac);
            SanPham sanPham = new SanPham();
            sanPham.setId(idSp);
            // Set các giá trị vào đối tượng
            chiTietSp.setSanPham(sanPham);
            chiTietSp.setMauSac(mauSac);
            chiTietSp.setNsx(nsx);
            chiTietSp.setDongSp(dongSp);
            // Thêm vào database
            BeanUtils.populate(chiTietSp, request.getParameterMap());
            if (chiTietSanPhamRepository.insert(chiTietSp)) {
                request.getSession().setAttribute("message", "Thêm thành công");
                response.sendRedirect(request.getContextPath() + "/chi-tiet-san-pham/index");
            }else {
                request.getSession().setAttribute("mess_error", "Thêm thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        try {
            // Lấy các giá trị từ form
            UUID idDong = UUID.fromString(request.getParameter("idDong"));
            UUID idNSX = UUID.fromString(request.getParameter("idNSX"));
            UUID idMauSac = UUID.fromString(request.getParameter("idMauSac"));
            UUID idSp = UUID.fromString(request.getParameter("idSp"));
            String id = request.getParameter("id");
            // Tạo đối tượng
            DongSp dongSp = new DongSp();
            dongSp.setId(idDong);
            NSX nsx = new NSX();
            nsx.setId(idNSX);
            MauSac mauSac = new MauSac();
            mauSac.setId(idMauSac);
            SanPham sanPham = new SanPham();
            sanPham.setId(idSp);
            // Set các giá trị vào đối tượng
            ChiTietSp chiTietSp = new ChiTietSp();
            chiTietSp.setSanPham(sanPham);
            chiTietSp.setMauSac(mauSac);
            chiTietSp.setNsx(nsx);
            chiTietSp.setDongSp(dongSp);
            BeanUtils.populate(chiTietSp, request.getParameterMap());
            if (chiTietSanPhamRepository.update(id,chiTietSp)) {
                request.getSession().setAttribute("message", "Update thành công");
                response.sendRedirect(request.getContextPath() + "/chi-tiet-san-pham/index");
            }else {
                request.getSession().setAttribute("mess_error", "Update thất bại");
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }


    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        // Lấy danh sách các đối tượng
        List<NSX> listNSX = nsxRepository.getAll();
        request.setAttribute("listNSX", listNSX);
        List<DongSp> listDongSp = dongSpRepository.getAll();
        request.setAttribute("listDongSp", listDongSp);
        List<MauSac> listMauSac = mauSacRepository.getAll();
        request.setAttribute("listMauSac", listMauSac);
        List<SanPham> listSanPham = sanPhamRepository.findAll();
        // Gửi danh sách các đối tượng qua view
        request.setAttribute("listSanPham", listSanPham);
        request.setAttribute("view_chiTietSanPham", "/views/admin/chiTietSanPham/create.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }
    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<ChiTietSanPhamViewModel> list = chiTietSanPhamRepository.getList();
        String realPath = request.getServletContext().getRealPath("/images");

        // Thay đổi đường dẫn tới ảnh để hiển thị ảnh thay vì đường dẫn
        for (ChiTietSanPhamViewModel sp : list) {
            String fileName = sp.getSrcImage();
            if (fileName != null) {
                sp.setSrcImage(request.getContextPath() + "/images/" + fileName);
            }
        }
        request.setAttribute("list", list);
        request.setAttribute("view_chiTietSanPham", "/views/admin/chiTietSanPham/index.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }
    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
// Lấy danh sách các đối tượng
        List<NSX> listNSX = nsxRepository.getAll();
        request.setAttribute("listNSX", listNSX);
        List<DongSp> listDongSp = dongSpRepository.getAll();
        request.setAttribute("listDongSp", listDongSp);
        List<MauSac> listMauSac = mauSacRepository.getAll();
        request.setAttribute("listMauSac", listMauSac);
        List<SanPham> listSanPham = sanPhamRepository.findAll();
        request.setAttribute("listSanPham", listSanPham);
        // Lấy id từ url
        String idCtsp = request.getParameter("id");
        ChiTietSp chiTietSp = new ChiTietSp();
        request.setAttribute("idDongsp", chiTietSanPhamRepository.getIdDongSp(idCtsp));
        request.setAttribute("idMauSac", chiTietSanPhamRepository.getIdMauSac(idCtsp));
        request.setAttribute("idNSX", chiTietSanPhamRepository.getIdNhaSanXuat(idCtsp));
        request.setAttribute("idSp", chiTietSanPhamRepository.getIdSanPham(idCtsp));
        ChiTietSp chiTietSpRepo = chiTietSanPhamRepository.getById(idCtsp);
        request.setAttribute("chiTietSp", chiTietSpRepo);
        request.setAttribute("view_chiTietSanPham", "/views/admin/chiTietSanPham/edit.jsp");
        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
    }
    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String id = request.getParameter("id");
        ChiTietSp chiTietSp = chiTietSanPhamRepository.getById(id);
        chiTietSanPhamRepository.delete(chiTietSp);
        response.sendRedirect("/Assignment_Sof3011_war_exploded/chi-tiet-san-pham/index");

    }
}
