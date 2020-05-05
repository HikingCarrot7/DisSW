package com.sw.controller;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 *
 * @author Nicolás
 */
public class VistaGraficoBarrasController implements Initializable, Observer
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
        data.getData().forEach(this::displayLabelForData);
    }

    private void displayLabelForData(XYChart.Data<String, Integer> data)
    {
        final Node node = data.getNode();
        final Label dataText = new Label(data.getYValue() + "");

        node.parentProperty().addListener((ov, oldParent, parent) ->
        {
            Group parentGroup = (Group) parent;
            parentGroup.getChildren().add(dataText);
        });

        node.boundsInParentProperty().addListener((ov, oldBounds, bounds) ->
        {
            dataText.setLayoutX(Math.round(bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2));
            dataText.setLayoutY(Math.round(bounds.getMinY() - dataText.prefHeight(-1) * 0.5));
        });
    }

    @Override
    public void update(Observable o, Object arg)
    {
        int candidato = (int) arg;
        data.getData().get(candidato).setYValue(data.getData().get(candidato).getYValue() + 1);
    }

}
