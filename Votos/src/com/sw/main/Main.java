package com.sw.main;

import com.sw.controller.WindowFactory;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
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

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        Observer graficaBarras = (Observer) WindowFactory.createWindow(
                "/com/sw/view/VistaGraficoBarras.fxml",
                null,
                100, 100);

        Observer graficaCircular = (Observer) WindowFactory.createWindow(
                "/com/sw/view/VistaGraficoCircular.fxml",
                null,
                200, 200);

        Observable votosController = (Observable) WindowFactory.createWindow(
                "/com/sw/view/VistaVotos.fxml",
                "/com/sw/styles/Stylesheet.css",
                300, 300);

        votosController.addObserver(graficaBarras);
        votosController.addObserver(graficaCircular);
    }

}
