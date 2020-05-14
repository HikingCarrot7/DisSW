package com.sw.controller;

import com.sw.main.Main;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Nicolás
 */
public class WindowFactory
{

    public static Object createWindow(String ruta, String stylesheet, int x, int y)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(ruta));
            Pane ventana = (Pane) loader.load();
            Scene scene = new Scene(ventana);

            if (stylesheet != null)
                scene.getStylesheets().add(stylesheet);

            Stage stage = new Stage();
            ((Controller) loader.getController()).initStage(stage);
            stage.setScene(scene);
            stage.setX(x);
            stage.setY(y);
            stage.setOnCloseRequest(e -> Platform.exit());
            stage.setAlwaysOnTop(true);
            stage.show();

            return loader.getController();

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        return null;
    }
}
