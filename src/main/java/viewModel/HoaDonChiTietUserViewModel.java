package viewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HoaDonChiTietUserViewModel
{
        private String ma;
        private String tenNguoiNhan;
        private String sdt;
        private String diaChi;
        private Date ngayTao;
        private Date ngayShip;
        private Date ngayNhan;
        private Date ngayThanhToan;
        private int tinhTrang;

        public String getTinhTrang() {
                if (tinhTrang == 0){
                        return "Chờ giao hàng";
                }else if (tinhTrang == 1){
                        return "Đang giao hàng";
                }   else if (tinhTrang == 2){
                        return "Đã Nhận hàng";
                }else {
                        return "Không xác định";
                }
        }
}
