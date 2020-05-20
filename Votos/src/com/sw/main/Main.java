package com.sw.main;

import com.sw.controller.WindowFactory;
import com.sw.model.GeneradorExcel;
import com.sw.model.Observado;
import com.sw.model.Observador;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author Nicolás
 */
public class Main extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override public void start(Stage primaryStage) throws IOException
    {
        Observador graficaBarras = (Observador) WindowFactory.createWindow(
                "/com/sw/view/VistaGraficoBarras.fxml",
                null,
                100, 100);

        Observador graficaCircular = (Observador) WindowFactory.createWindow(
                "/com/sw/view/VistaGraficoCircular.fxml",
                null,
                200, 200);

        Observado votosController = (Observado) WindowFactory.createWindow(
                "/com/sw/view/VistaVotos.fxml",
                "/com/sw/styles/Stylesheet.css",
                300, 300);

        votosController.addObservador(graficaBarras);
        votosController.addObservador(graficaCircular);

        GeneradorExcel generador = new GeneradorExcel();
        votosController.addObservador(generador);
    }

}
