package viewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GioHangChiTietViewModel {
    private UUID id;
    private UUID idGioHang;
    private String tenSp;
    private Integer soLuong;
    private BigDecimal donGia;
    private String srcImage;

}
