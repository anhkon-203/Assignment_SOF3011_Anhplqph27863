package viewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


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
    private Integer soLuong;
    private BigDecimal donGia;

}
