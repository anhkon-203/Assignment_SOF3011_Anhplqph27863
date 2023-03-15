/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;



import entitis.DongSp;

import java.util.List;

/**
 *
 * @author anhkon
 */
public interface DongSpService {

    public boolean them(DongSp dongSp);

    public boolean sua(String ma, DongSp dongSp);

    public boolean xoa(DongSp dongSp);

    public List<String> check(String ma);

    public List<DongSp> getList();
}
