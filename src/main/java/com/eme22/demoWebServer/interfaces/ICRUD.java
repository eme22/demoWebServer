/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eme22.demoWebServer.interfaces;

import com.eme22.demoWebServer.model.Usuario;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author iv_rf
 */

public interface ICRUD <T> {
    
    public abstract Boolean insert(T t) throws SQLException;
    public abstract Boolean update(T t) throws SQLException;
    public abstract Boolean delete(@Nullable Integer id) throws SQLException;
    public abstract Boolean delete(Usuario usuario) throws SQLException;
    public abstract T selectById(int id) throws SQLException;
    public abstract List<T> selectAll() throws SQLException;
    public abstract List<T> search(String username) throws SQLException;
}
