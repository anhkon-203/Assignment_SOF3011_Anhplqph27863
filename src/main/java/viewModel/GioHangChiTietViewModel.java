package viewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GioHangChiTietViewModel {
    private String id;
    private String idGioHang;
    private String tenSp;
    private Integer soLuong;
    private BigDecimal donGia;
    private String srcImage;

}
