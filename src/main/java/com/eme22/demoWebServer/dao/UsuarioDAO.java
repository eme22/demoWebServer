package com.eme22.demoWebServer.dao;

import com.eme22.demoWebServer.connection.Connection;
import com.eme22.demoWebServer.interfaces.IUsuario;
import com.eme22.demoWebServer.model.Usuario;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IUsuario {

    private static PreparedStatement pstm=null;
    private static ResultSet res = null;
    private static Connection con;

    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        con = Connection.getInstance();
    }

    @Override
    public Boolean insert(Usuario usuario) throws SQLException {
        if (usuario == null) return false;
        String SQL_LOGIN="insert into usuarios set id = ?, nombre = ?, usuario = ?, contrasenia = ? on duplicate key update nombre = ?,  contrasenia = ?";
        pstm = con.getConnection().prepareStatement(SQL_LOGIN);
        pstm.setInt(1, usuario.getId());
        pstm.setString(2, usuario.getNombre());
        pstm.setString(3, usuario.getUsuario());
        pstm.setString(4, usuario.getContrasenia());
        pstm.setString(5, usuario.getNombre());
        pstm.setString(6, usuario.getContrasenia());
        boolean result = pstm.executeUpdate() > 0;
        System.out.println(result);
        con.close();
        return result;
    }

    @Override
    public Boolean update(Usuario usuario) throws SQLException {
        if (usuario == null) return false;
        Usuario old = selectByUser(usuario.getUsuario());
        if (old == null) return insert(usuario);
        else {
            boolean isSame = old.equals(usuario);
            if (isSame) return false;
            String SQL_LOGIN="update usuarios set nombre = ?, usuario = ?,contrasenia = ? where id = ?";
            pstm = con.getConnection().prepareStatement(SQL_LOGIN);
            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getUsuario());
            pstm.setString(3, usuario.getContrasenia());
            pstm.setInt(4, usuario.getId());
            boolean result = pstm.executeUpdate() > 0;
            System.out.println(result);
            con.close();
            return result;
        }

    }

    @Override
    public Boolean delete(@Nullable Integer id) throws SQLException {
        String SQL_LOGIN;
        if (id == null) SQL_LOGIN="truncate usuarios";
        else SQL_LOGIN="delete from usuarios where id=?";

        pstm = con.getConnection().prepareStatement(SQL_LOGIN);
        if (id != null) pstm.setInt(1,id);
        boolean result = pstm.executeUpdate() > 0;
        System.out.println(result);
        con.close();
        return result;
    }

    @Override
    public Boolean delete(Usuario usuario) throws SQLException {
        String SQL_LOGIN="delete from usuarios where usuario=?";
        pstm = con.getConnection().prepareStatement(SQL_LOGIN);
        pstm.setString(1, usuario.getUsuario());
        boolean result = pstm.executeUpdate() > 0;
        System.out.println(result);
        con.close();
        return result;
    }

    @Override
    public Usuario selectById(int id) throws SQLException {
        final String SQL_LOGIN="select * from usuarios where id=?";
        pstm = con.getConnection().prepareStatement(SQL_LOGIN);
        pstm.setInt(1,id);
        res = pstm.executeQuery();
        con.close();
        if (!res.next()) return null;
        else return new Usuario(res.getInt(1),res.getString(2),res.getString(3), res.getString(4));
    }

    @Override
    public List<Usuario> search(String username) throws SQLException {
        List<Usuario> data = new ArrayList<>();
        final String SQL_LOGIN="select * from usuarios where nombre like ? ";
        pstm = con.getConnection().prepareStatement(SQL_LOGIN);
        pstm.setString(1, "%"+username+"%");
        res = pstm.executeQuery();
        con.close();
        while (res.next()){
            data.add(new Usuario(res.getInt(1),res.getString(2),res.getString(3), res.getString(4)));
        }
        return data;
    }

    @Override
    public List<Usuario> selectAll() throws SQLException {
        List<Usuario> data = new ArrayList<>();
        final String SQL_LOGIN="select * from usuarios";
        pstm = con.getConnection().prepareStatement(SQL_LOGIN);
        res = pstm.executeQuery();
        con.close();
        while (res.next()){
            data.add(new Usuario(res.getInt(1),res.getString(2),res.getString(3), res.getString(4)));
        }
        return data;
    }

    @Override
    public Boolean login(Usuario usuario) throws SQLException {
        final String SQL_LOGIN="select * from usuarios where usuario=? and contrasenia=?";

        pstm = con.getConnection().prepareStatement(SQL_LOGIN);
        pstm.setString(1,usuario.getUsuario());
        pstm.setString(2,usuario.getContrasenia());
        res = pstm.executeQuery();
        con.close();
        return res.next();
    }

    @Override
    public Usuario selectByUser(String usuario) throws SQLException {
        final String SQL_LOGIN="select * from usuarios where usuario=?";
        pstm = con.getConnection().prepareStatement(SQL_LOGIN);
        pstm.setString(1,usuario);
        res = pstm.executeQuery();
        con.close();
        if (!res.next()) return null;
        else return new Usuario(res.getInt(1),res.getString(2),res.getString(3), res.getString(4));
    }
}
