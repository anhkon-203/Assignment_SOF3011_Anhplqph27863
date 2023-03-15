package entitis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "GioHangChiTiet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GioHangChiTiet implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "idGioHang")
    private GioHang gioHang;

    @Id
    @ManyToOne
    @JoinColumn(name = "idChiTietSp")
    private ChiTietSp chiTietSp;

    @Column(name = "SoLuong")
    private int soLuongTon;

    @Column(name = "DonGia", columnDefinition = "Decimal(20,0)")
    private Float donGia;

    @Column(name = "DonGiaKhiGiam", columnDefinition = "Decimal(20,0)")
    private Float donGiaKhiGiam;


}
