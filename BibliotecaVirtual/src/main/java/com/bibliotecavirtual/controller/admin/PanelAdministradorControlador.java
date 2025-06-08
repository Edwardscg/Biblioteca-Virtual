package com.bibliotecavirtual.controller.admin;

import com.bibliotecavirtual.controller.UIHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;

public class PanelAdministradorControlador {

    @FXML StackPane panelContenido;
    @FXML Button btnSubirLibro, btnEditarLibro, btnEliminarLibro, btnVerLibros, btnVerValoraciones;
    @FXML Button btnVerUsuarios, btnVerMembresias, btnAlquileresPorUsuario, btnLibrosCompradosPorUsuario, btnValoracionesPorUsuario;
    @FXML Button btnAlquileresActivos;
    @FXML Button btnMovimientosMonedas;

    @FXML Button btnCerrarSesion;

    @FXML
    public void inicializar(){

        btnSubirLibro.setOnAction(this::cargarVistaSubirLibro);

    }

    private void cargarVistaSubirLibro(ActionEvent e){

        cargarContenido("/view/admin/SubirLibro.fxml");
    }

    private void cargarContenido(String fxml_ubicacion){

        try {

            Parent root = FXMLLoader.load(getClass().getResource(fxml_ubicacion));
            panelContenido.getChildren().setAll(root);
        }catch (Exception e){

            e.printStackTrace();
            UIHelper.mostrarAlerta("Error", "No se pudo cargar el contenido: "+ fxml_ubicacion);
        }
    }

}
