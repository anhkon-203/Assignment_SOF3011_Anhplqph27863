package controlllers;

import entitis.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import services.*;
import services.impl.*;
import viewModel.*;

import java.io.IOException;
import java.util.List;


@WebServlet({
        "/chi-tiet-san-pham/index",    // GET
        "/chi-tiet-san-pham/create",   // GET
        "/chi-tiet-san-pham/edit",     // GET
        "/chi-tiet-san-pham/delete",   // GET
        "/chi-tiet-san-pham/store",    // POST
        "/chi-tiet-san-pham/update",   // POST
})

public class ChiTietSanPhamServlet extends HttpServlet {
    private SanPhamService sanPhamService = new SanPhamServiceImpl();
    private NhaSanXuatService nsxService = new NhaSanXuatServiceImpl();
    private MauSacService mausacService = new MauSacServiceImpl();
    private DongSpService dongSanPhamService = new DongSpServiceImpl();
    private ChiTietSanPhamService chiTietSanPhamService = new ChiTietSanPhamServiceImpl();
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

            // Lấy các giá trị từ form
            String idDong = request.getParameter("idDong");
            String idNSX = request.getParameter("idNSX");
            String idMauSac = request.getParameter("idMauSac");
            String idSp = request.getParameter("idSp");
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
            BeanUtils.populate(chiTietSp, request.getParameterMap());
            chiTietSanPhamService.them(chiTietSp);
        } catch (Exception e) {
            e.printStackTrace();
        }
       response.sendRedirect("/Assignment_Sof3011_war_exploded/chi-tiet-san-pham/index");

    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<NSX> listNSX = nsxService.getList();
        request.setAttribute("listNSX", listNSX);
        List<DongSp> listDongSp = dongSanPhamService.getList();
        request.setAttribute("listDongSp", listDongSp);
        List<MauSac> listMauSac = mausacService.getList();
        request.setAttribute("listMauSac", listMauSac);
        List<SanPham> listSanPham = sanPhamService.getList();
        request.setAttribute("listSanPham", listSanPham);
        request.getRequestDispatcher("/views/chiTietSanPham/create.jsp")
                .forward(request, response);
    }
    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<ChiTietSanPhamViewModel> list = chiTietSanPhamService.getList();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/views/chiTietSanPham/index.jsp")
                .forward(request, response);
    }
}
