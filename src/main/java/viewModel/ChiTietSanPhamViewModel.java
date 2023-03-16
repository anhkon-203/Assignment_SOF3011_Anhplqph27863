package viewModel;

import entitis.DongSp;
import entitis.MauSac;
import entitis.NSX;
import entitis.SanPham;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ChiTietSanPhamViewModel {
    private String id;
    
    private int namBaoHanh;

    private String moTa;

    private int soLuongTon;

    private Integer giaNhap;

    private Integer giaBan;

    private String tenSp;

    private String tenNSX;

    private String tenMauSac;

    private String tenDongSp;


}