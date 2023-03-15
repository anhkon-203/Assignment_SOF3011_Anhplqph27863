/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entitis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author anhkon
 */
@Entity
@Table(name = "ChiTietSanPham")
@Getter
@Setter
@NoArgsConstructor
public class ChiTietSp {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private String id;

    @Column(name = "NamBH")
    private int namBaoHanh;

    @Column(name = "MoTa", columnDefinition = "Nvarchar(100)")
    private String moTa;

    @Column(name = "SoLuongTon")
    private int soLuongTon;

    @Column(name = "GiaNhap", columnDefinition = "Decimal(20,0)")
    private Integer giaNhap;

    @Column(name = "GiaBan", columnDefinition = "Decimal(20,0)")
    private Integer giaBan;
    @ManyToOne
    @JoinColumn(name = "idSp")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "idNSX")
    private NSX nsx;

    @ManyToOne
    @JoinColumn(name = "idMauSac")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "idDong")
    private DongSp dongSp;
    @OneToMany(mappedBy = "chiTietSp", fetch = FetchType.LAZY)
    private List<GioHangChiTiet> listGioHangChiTiet;

    @OneToMany(mappedBy = "chiTietSp", fetch = FetchType.LAZY)
    private List<HoaDonChiTiet> listHoaDonChiTiet;


}
