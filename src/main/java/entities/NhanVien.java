package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "NhanVien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private String id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "TenDem")
    private String tenDem;

    @Column(name = "Ho")
    private String ho;

    @Column(name = "GioiTinh")
    private String gioiTinh;

    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "Sdt")
    private String sdt;

    @Column(name = "MatKhau")
    private String matKhau;

    @Column(name = "Email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "idCH")
    private CuaHang cuaHang;

    @ManyToOne
    @JoinColumn(name = "idCV")
    private ChucVu chucVu;

    @ManyToOne
    @JoinColumn(name = "idGuiBC")
    private NhanVien nhanVien;

    @Column(name = "TrangThai")
    private int trangThai;

    @OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY)
    private List<HoaDon> listHoaDon;


}
