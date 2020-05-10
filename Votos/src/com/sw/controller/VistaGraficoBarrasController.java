package com.sw.controller;

import com.sw.model.Candidato;
import com.sw.model.Observado;
import com.sw.model.Observador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Nicolás
 */
public class VistaGraficoBarrasController implements Initializable, Observador
{

    @FXML private BarChart<String, Integer> graficoBarras;

    private XYChart.Series<String, Integer> data;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        data = new XYChart.Series<>();
        data.getData().add(new XYChart.Data<>("Candidato 1", 0));
        data.getData().add(new XYChart.Data<>("Candidato 2", 0));
        data.getData().add(new XYChart.Data<>("Candidato 3", 0));

        graficoBarras.getData().add(data);
    }

    @Override
    public void update(Observado o, Object arg)
    {
        Candidato candidato = (Candidato) arg;
        data.getData().get(candidato.getnCandidato()).setYValue(candidato.getnVotos());
    }

}
