package com.sw.controller;

import com.sw.model.Observado;
import com.sw.model.Observador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * @author Nicolás
 */
public class VistaGraficoBarrasController implements Initializable, Observador, Controller
{

    private Stage stage;
    @FXML private BarChart<String, Integer> graficoBarras;

    private XYChart.Series<String, Integer> data;

    @Override public void initialize(URL url, ResourceBundle rb)
    {
        data = new XYChart.Series<>();
        data.getData().add(new XYChart.Data<>("Candidato 1", 0));
        data.getData().add(new XYChart.Data<>("Candidato 2", 0));
        data.getData().add(new XYChart.Data<>("Candidato 3", 0));

        graficoBarras.getData().add(data);
    }

    @Override public void initStage(Stage stage)
    {
        this.stage = stage;
    }

    @Override public void update(Observado o, Object arg)
    {
        int[] votos = (int[]) arg;

        for (int i = 0; i < votos.length; i++)
            data.getData().get(i).setYValue(votos[i]);
    }

}
