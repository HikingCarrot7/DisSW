package com.sw.controller;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

/**
 *
 * @author Nicolás
 */
public class VistaGraficoCircularController implements Initializable, Observer
{

    @FXML private PieChart graficoCircular;

    private ObservableList<PieChart.Data> pieChartData;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Candidato 1", 0),
                new PieChart.Data("Candidato 2", 0),
                new PieChart.Data("Candidato 3", 0));

        graficoCircular.setLabelLineLength(10);
        graficoCircular.setLegendSide(Side.LEFT);
        graficoCircular.setData(pieChartData);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        int candidato = (int) arg;
        pieChartData.get(candidato).setPieValue(pieChartData.get(candidato).getPieValue() + 1);
    }

}
