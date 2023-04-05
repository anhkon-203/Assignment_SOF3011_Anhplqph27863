package viewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HoaDonChiTietViewModel {
    private String maHD;
    private String tenNguoiNhan;
    private String diaChi;
    private String sdt;
    private int tinhTrang;
    private String tenSP;
    private String srcImage;
    private Integer soLuong;
    private BigDecimal donGia;

    public String getTinhTrang() {
        if (tinhTrang == 0){
            return "Chờ giao hàng";
        }else if (tinhTrang == 1){
            return "Đang giao hàng";
        }   else if (tinhTrang == 2){
            return "Đã giao hàng";
        }else {
            return "Không xác định";
        }
    }

}
