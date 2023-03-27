package viewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ChiTietSanPhamViewModel {
    private String id;

    private int namBaoHanh;

    private String moTa;

    private Integer soLuongTon;

    private BigDecimal giaNhap;

    private BigDecimal giaBan;

    private String tenSp;

    private String tenNSX;

    private String tenMauSac;

    private String tenDongSp;

    private String srcImage;


}