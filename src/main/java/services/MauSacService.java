/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import entitis.MauSac;
import viewModel.MauSacViewModel;

import java.util.List;

/**
 *
 * @author anhkon
 */
public interface MauSacService {

    public boolean them(MauSac mauSac);

    public boolean sua(String ma, MauSac mauSac);

    public boolean xoa(MauSac mauSac);

    public List<String> check(String ma);

    public List<MauSac> getList();
}
