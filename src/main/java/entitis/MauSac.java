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
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author anhkon
 */
@Entity
@Table(name = "MauSac")
@Getter
@Setter
@NoArgsConstructor
public class MauSac implements Serializable{
    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "idMauSac", columnDefinition = "uniqueidentifier")
    private String idMau;

    @Column(name = "ma", unique = true)
    private String ma;
    @Column(name = "ten",columnDefinition =  "Nvarchar(100)")
    private String ten;
    @OneToMany(mappedBy = "mauSac", fetch = FetchType.LAZY)
    private List<ChiTietSp> lstSanPham;

}
