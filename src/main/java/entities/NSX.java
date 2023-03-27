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
import java.util.UUID;

/**
 *
 * @author anhkon
 */
@Entity
@Table(name = "NSX")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NSX implements Serializable{
    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private String id;

    @Column(name = "ma")
    private String ma;
    @Column(name = "ten")
    private String ten;
    @OneToMany(mappedBy = "nsx", fetch = FetchType.LAZY)
    private List<ChiTietSp> lstSP;


    
}
