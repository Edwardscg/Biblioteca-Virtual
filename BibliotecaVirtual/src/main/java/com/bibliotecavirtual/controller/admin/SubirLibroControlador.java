package com.bibliotecavirtual.controller.admin;

import com.bibliotecavirtual.controller.UIHelper;
import com.bibliotecavirtual.logic.LibroService;
import com.bibliotecavirtual.model.Libro;
import com.bibliotecavirtual.util.BackblazeUploader;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;

public class SubirLibroControlador {

    @FXML private TextField txtTitulo;
    @FXML private TextField txtAutor;
    @FXML private TextField txtGenero;
    @FXML private TextArea areaDescripcion;
    @FXML private TextField txtPrecio;
    @FXML private DatePicker pickerFechaPublicacion;
    @FXML private Label lblNombrePDF;
    @FXML private Label lblNombrePortada;

    private File archivoPDF;
    private File imagenPortada;

    @FXML
    public void seleccionarPDF(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        archivoPDF = fileChooser.showOpenDialog(new Stage());

        if(archivoPDF !=null){

            lblNombrePDF.setText(archivoPDF.getName());
        }else {

            lblNombrePDF.setText("Ningun archivo seleccionado.");
        }
    }

    @FXML
    public void seleccionarPortada(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen de portada");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagenes", "*.jpg", "*.jpeg", "*.png", "*.webp"));
        imagenPortada = fileChooser.showOpenDialog(new Stage());

        if(imagenPortada!= null){

            lblNombrePortada.setText(imagenPortada.getName());
        }else {

            lblNombrePortada.setText("Ninguna imagen seleccionada.");
        }
    }

    @FXML
    public void subirLibro(){

        if(txtTitulo.getText().isEmpty() || txtAutor.getText().isEmpty() || txtGenero.getText().isEmpty() || areaDescripcion.getText().isEmpty()|| txtPrecio.getText().isEmpty() || pickerFechaPublicacion.getValue() == null || archivoPDF == null || imagenPortada == null){

            UIHelper.mostrarAlerta("Compos Incompletos", "Complete todos los campos y selecciona los archivos.");
            return;
        }

        try{

            BackblazeUploader uploader = new BackblazeUploader();
            String nombre_pdf = "libros/" + System.currentTimeMillis() + "_" + archivoPDF.getName();
            String nombre_portada = "portadas/" + System.currentTimeMillis() + "_" + imagenPortada.getName();

            String pdf_url = uploader.getPublicUrl(uploader.uploadFile(archivoPDF, nombre_pdf, "application/pdf"));
            String portada_url = uploader.getPublicUrl(uploader.uploadFile(imagenPortada, nombre_portada, Files.probeContentType(imagenPortada.toPath())));

            Libro libro = new Libro(0,txtTitulo.getText(), txtAutor.getText(), txtGenero.getText(), areaDescripcion.getText(), pdf_url, portada_url, Integer.parseInt(txtPrecio.getText()), 0, 0, pickerFechaPublicacion.getValue());

            LibroService libroService = new LibroService();
            libroService.agregarLibro(libro);

            UIHelper.mostrarAlerta("Éxito", "El libro ha sido subido correctamente.");
            //limpiarCampos();

        } catch (Exception e) {
            e.printStackTrace();
            UIHelper.mostrarAlerta("Error", "Nose pudo subir el libro: "+ e.getMessage());
        }
    }

    private void limpiarCampos(){

        txtTitulo.clear();
        txtAutor.clear();
        txtGenero.clear();
        areaDescripcion.clear();
        archivoPDF = null;
        imagenPortada = null;
        lblNombrePDF.setText("Ningún archivo seleccionado");
        lblNombrePortada.setText("Ninguna imagen seleccionada");
        txtPrecio.clear();
        pickerFechaPublicacion.setValue(null);
    }
}
