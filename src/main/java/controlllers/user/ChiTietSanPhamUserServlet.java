package controlllers.user;

import entities.KhachHang;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repositories.ChiTietSanPhamRepository;
import viewModel.ChiTietSanPhamViewModel;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/ChiTietSanPhamUserServlet")
public class ChiTietSanPhamUserServlet extends HttpServlet {
    private ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.index(request, response);
    }
    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("user");
        String id = request.getParameter("id");
        String tenDongSp = chiTietSanPhamRepository.selectTenDongSp(id);
        List<ChiTietSanPhamViewModel> sanPham_Category = chiTietSanPhamRepository.getListByTenDongsp(tenDongSp);
        List<ChiTietSanPhamViewModel> list = chiTietSanPhamRepository.userGetById(id);
        request.setAttribute("sanPham", list);
        request.setAttribute("sanPham_Category", sanPham_Category);
        String realPath = request.getServletContext().getRealPath("/images");

        // Thay đổi đường dẫn tới ảnh để hiển thị ảnh thay vì đường dẫn
        for (ChiTietSanPhamViewModel sp : list  ) {
            String fileName = sp.getSrcImage();
            if (fileName != null) {
                sp.setSrcImage(request.getContextPath() + "/images/" + fileName);
            }
        }
        for (ChiTietSanPhamViewModel sp : sanPham_Category  ) {
            String fileName = sp.getSrcImage();
            if (fileName != null) {
                sp.setSrcImage(request.getContextPath() + "/images/" + fileName);
            }
        }

        request.setAttribute("view", "/views/user/sanPham/chiTietSanPham.jsp");
        request.getRequestDispatcher("/views/user/layoutUser.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
