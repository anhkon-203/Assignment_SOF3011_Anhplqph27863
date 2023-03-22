package viewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GioHangChiTietViewModel {
    private String id;
    private String idGioHang;
    private String tenSp;
    private int soLuong;
    private Float donGia;
    private String srcImage;

}
