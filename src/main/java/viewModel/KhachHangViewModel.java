package viewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class KhachHangViewModel {
    private String ma;
    private String ho;
    private String tenDem;
    private String ten;
    private Date ngaySinh;
    private String sdt;
    private String diaChi;
    private String quocGia;
    private String thanhPho;
    private String email;


}
