package com.bibliotecavirtual.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class VerificacionCodigoControlador {

    @FXML
    private TextField txtCodigo;
    @FXML
    private Label lblCorreo;

    private String codigo_correcto;
    private VerificacionExitosa verificacionExitosa;

    public void inicializarDatos(String correo, String codigo, VerificacionExitosa verificacionExitosa){

        this.codigo_correcto = codigo;
        this.verificacionExitosa = verificacionExitosa;
        lblCorreo.setText("Codigo enviado a: "+ correo);
    }

    @FXML
    private void handleVerificarCodigo(){

        String ingresado = txtCodigo.getText().trim();

        if(ingresado.isEmpty()){

            UIHelper.mostrarAlerta("Error", "Tiene que ingresar el código.");
            return;
        }

        if(ingresado.equals(codigo_correcto)){

            try{

                if(verificacionExitosa!=null){

                    verificacionExitosa.ejecutarAccion();
                }

                UIHelper.mostrarAlerta("Acción exitosa", "Codigo verificado correctamente.");
                cerrarVentana();

            }catch (Exception e){
                UIHelper.mostrarAlerta("Error", "No se pudo ejecutar la acción.");
            }
        } else{

            UIHelper.mostrarAlerta("Código incorrecto", "Ingrese el código que recibió en su correo.");
        }
    }

    private void cerrarVentana(){

        Stage ventana_actual = (Stage) txtCodigo.getScene().getWindow();
        UIHelper.cerrarVentana(ventana_actual);
    }
}
