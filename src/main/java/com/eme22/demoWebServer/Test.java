package com.eme22.demoWebServer;

import com.eme22.demoWebServer.dao.UsuarioDAO;
import com.eme22.demoWebServer.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        UsuarioDAO dao = null;
        try {
            dao = new UsuarioDAO();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            List<Usuario> data = dao.selectAll();
            for (Usuario usuario: data) {
                System.out.println(usuario.getUsuario());
                System.out.println(usuario.getContrasenia());
                System.out.print("\n");
            }
            dao.insert(new Usuario(0,"aa","aa","aa"));
            dao.insert(new Usuario(0,"bb","bb","bb"));
            data = dao.selectAll();
            for (Usuario usuario: data) {
                System.out.println(usuario.getUsuario());
                System.out.println(usuario.getContrasenia());
                System.out.print("\n");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
