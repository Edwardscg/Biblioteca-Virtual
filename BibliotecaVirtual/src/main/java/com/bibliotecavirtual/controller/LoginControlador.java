package com.bibliotecavirtual.controller;

import com.bibliotecavirtual.logic.UsuarioService;
import com.bibliotecavirtual.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginControlador {

    public LoginControlador(){}

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtContraseña;

    @FXML
    private Hyperlink hyperlinkrecuperarContraseña;

    @FXML
    private void handleIniciarSesion() throws Exception {

        String correo = txtCorreo.getText();
        String contraseña = txtContraseña.getText();

        if(correo.isEmpty() || contraseña.isEmpty()){

            UIHelper.mostrarAlerta("Entradas vacías", "Llene todos los campos.");
            return;
        }

        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.loguearUsuario(correo, contraseña);

        if(usuario!=null){

            Stage ventana_actual = (Stage) txtCorreo.getScene().getWindow();
            UIHelper.abrirVentana(ventana_actual, "/view/admin/PanelAdministrador.fxml", "Administrador");

        }else {

            UIHelper.mostrarAlerta("Error", "Correo o contraseña incorrectos.");
        }
    }

    @FXML
    private void handleAbrirRegistro(){

        Stage ventana_actual = (Stage) txtCorreo.getScene().getWindow();
        UIHelper.abrirVentana(ventana_actual, "/view/ventanas/Registro.fxml", "Registro");
    }

    @FXML
    private void handleRecuperarContraseña(){

        Stage ventana_actual = (Stage) hyperlinkrecuperarContraseña.getScene().getWindow();
        UIHelper.cambiarEscena(ventana_actual, "/view/ventanas/RecuperarContraseña.fxml", "Recuperar Contraseña");
    }
}
