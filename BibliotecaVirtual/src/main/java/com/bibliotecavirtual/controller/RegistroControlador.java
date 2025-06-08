package com.bibliotecavirtual.controller;

import com.bibliotecavirtual.logic.CorreoService;
import com.bibliotecavirtual.logic.UsuarioService;
import com.bibliotecavirtual.model.ClienteNormal;
import com.bibliotecavirtual.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class RegistroControlador {

    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtContraseña;
    @FXML private TextField txtConfirmarContraseña;

    @FXML
    private void handleRegistro() throws Exception{

        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contraseña = txtContraseña.getText();
        String confirmar = txtConfirmarContraseña.getText();

        if(nombre.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || confirmar.isEmpty()){

            UIHelper.mostrarAlerta("Error", "Todos los datos son obligatorios.");
            return;
        }

        if(!contraseña.equals(confirmar)){

            UIHelper.mostrarAlerta("Error", "Las contraseñas no coinciden.");
            return;
        }

        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.buscarUsuarioPorCorreo(correo);

        if(usuario!= null){

            UIHelper.mostrarAlerta("Error", "Ya existe un usuario registrado con ese correo.");
            return;
        }

        String codigo = CorreoService.generarCodigoDeVerificacion();
        CorreoService.enviarCodigoPorCorreo(correo, codigo);

        abrirVentanaDeVerificacion(correo, codigo, () -> {

            try {

                ClienteNormal clienteNormal = new ClienteNormal(0, nombre, correo, contraseña, "estudiante", 0);
                usuarioService.registrarUsuario(clienteNormal);

                Stage ventana_actual = (Stage) txtCorreo.getScene().getWindow();
                ventana_actual.close();

            }catch (Exception e){

                e.printStackTrace();
            }
        });
    }

    @FXML
    private void handleCancelar(){

        Stage ventana_actual = (Stage) txtCorreo.getScene().getWindow();
        UIHelper.abrirVentana(ventana_actual, "/view/ventanas/Login.fxml", "Inicio de Sesión");
    }

    private void abrirVentanaDeVerificacion(String correo, String codigo_generado, VerificacionExitosa verificacionExitosa){

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ventanas/VerificacionCodigo.fxml"));
            Parent root = loader.load();

            VerificacionCodigoControlador verificacionCodigoControlador = loader.getController();
            verificacionCodigoControlador.inicializarDatos(correo, codigo_generado, verificacionExitosa);

            Stage stage = new Stage();
            stage.setTitle("Verificación de Código");
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception e){

            e.printStackTrace();
            UIHelper.mostrarAlerta("Error", "No se pudo abrir la ventana de verificación.");
        }
    }
}
