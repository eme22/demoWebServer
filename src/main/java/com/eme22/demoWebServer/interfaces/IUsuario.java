/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eme22.demoWebServer.interfaces;

import com.eme22.demoWebServer.model.Usuario;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author iv_rf
 */

public interface IUsuario extends ICRUD<Usuario>{
    
    public abstract Boolean login(Usuario usuario) throws SQLException;
    public abstract Usuario selectByUser(String nombre) throws SQLException;
}
