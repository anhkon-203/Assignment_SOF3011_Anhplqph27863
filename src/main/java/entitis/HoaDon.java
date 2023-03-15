package entitis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "HoaDon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoaDon implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private String id;

    @ManyToOne
    @JoinColumn(name = "idKH")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "idNV")
    private NhanVien nhanVien;

    @Column(name = "Ma", columnDefinition = "Varchar(20)",unique = true)
    private String ma;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgayThanhToan")
    private Date ngayThanhToan;

    @Column(name = "NgayShip")
    private Date ngayShip;

    @Column(name = "NgayNhan")
    private Date ngayNhan;

    @Column(name = "TrangThai")
    private int trangThai;

    @Column(name = "TenNguoiNhan", columnDefinition = "Nvarchar(50)")
    private String tenNguoiNhan;

    @Column(name = "DiaChi", columnDefinition = "Nvarchar(100)")
    private String diaChi;

    @Column(name = "Sdt", columnDefinition = "Varchar(30)")
    private String sdt;

    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.LAZY)
    private List<HoaDonChiTiet> listHoaDonChiTiet;


}
