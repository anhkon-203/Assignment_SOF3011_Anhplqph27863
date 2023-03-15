package entitis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CuaHang")
@Getter
@Setter
@NoArgsConstructor
public class CuaHang implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private String id;

    @Column(name = "Ma", columnDefinition = "Varchar(20)",unique = true)
    private String ma;

    @Column(name = "Ten", columnDefinition = "Nvarchar(MAX)")
    private String ten;

    @Column(name = "DiaChi", columnDefinition = "Nvarchar(MAX)")
    private String diaChi;

    @Column(name = "ThanhPho", columnDefinition = "Nvarchar(MAX)")
    private String thanhPho;

    @Column(name = "QuocGia", columnDefinition = "Nvarchar(MAX)")
    private String quocGia;

    @OneToMany(mappedBy = "chucVu", fetch = FetchType.LAZY)
    private List<NhanVien> listNhanVien;

}
