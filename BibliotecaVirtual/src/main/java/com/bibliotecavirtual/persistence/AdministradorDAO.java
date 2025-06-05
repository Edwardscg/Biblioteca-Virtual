package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Usuario;
import javafx.collections.ObservableList;

public interface AdministradorDAO {

    ObservableList<Usuario> verUsuarios()throws Exception;
}
