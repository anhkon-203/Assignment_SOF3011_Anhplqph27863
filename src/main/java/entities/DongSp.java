/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import lombok.AllArgsConstructor;
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
@Table(name = "DongSp")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DongSp implements Serializable{
    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private String id;

    @Column(name = "ma", unique = true)
    private String ma;
    @Column(name = "ten",columnDefinition =  "Nvarchar(100)")
    private String ten;
    @OneToMany(mappedBy = "dongSp", fetch = FetchType.LAZY)
    private List<ChiTietSp> lstSanPham;


}
