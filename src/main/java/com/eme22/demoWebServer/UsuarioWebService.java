package com.eme22.demoWebServer;


import com.eme22.demoWebServer.dao.UsuarioDAO;
import com.eme22.demoWebServer.model.Usuario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(name = "usuario", serviceName = "usuario")
public class UsuarioWebService {

    @WebMethod(operationName = "insert")
    public Boolean insert(@WebParam(name = "usuario") Usuario usuario) throws SQLException, ClassNotFoundException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        return usuarioDAO.insert(usuario);

    }

    @WebMethod( operationName = "login")
    public Boolean login(@WebParam(name = "usuario")Usuario usuario) throws SQLException, ClassNotFoundException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        return usuarioDAO.login(usuario);

    }

    @WebMethod(operationName = "list")
    public List<Usuario> list() throws SQLException, ClassNotFoundException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.selectAll();
    }

    /*
    public Boolean delete(Usuario usuario) throws SQLException, ClassNotFoundException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.delete(usuario);
    }
    */
    public List<Usuario> search(String username) throws SQLException, ClassNotFoundException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.search(username);
    }

    public Boolean delete(int usuario) throws SQLException, ClassNotFoundException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.delete(usuario);
    }

    public Usuario select(String username) throws SQLException, ClassNotFoundException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.selectByUser(username);
    }

}
