package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Administrador;
import com.bibliotecavirtual.model.ClienteEstudiante;
import com.bibliotecavirtual.model.ClienteNormal;
import com.bibliotecavirtual.model.Usuario;
import javafx.collections.ObservableList;

public class AdministradorDAOImpl implements AdministradorDAO{

    @Override
    public ObservableList<Usuario> verUsuarios() throws Exception {

        String sql = "select * from usuario";

        return DBHelper.llenarTabla(sql, rs -> {

            String tipo_usuario = rs.getString("tipo");

            if(tipo_usuario.equals("normal")){

                return new ClienteNormal(rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("correo"), rs.getString("contrasena"), rs.getString("tipo"), rs.getInt("monedas"));

            } else if(tipo_usuario.equals("estudiante")){

                return new ClienteEstudiante(rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("correo"), rs.getString("contrasena"), rs.getString("tipo"), rs.getInt("monedas"));

            } else{

                return new Administrador(rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("correo"), rs.getString("contrasena"), rs.getString("tipo"));

            }
        });
    }
}
