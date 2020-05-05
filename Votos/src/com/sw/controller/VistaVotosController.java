package com.sw.controller;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author Nicolás
 */
public class VistaVotosController extends Observable implements Initializable
{

    @FXML private Button candidato1;
    @FXML private Button candidato2;
    @FXML private Button candidato3;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        candidato1.setGraphic(new ImageView("/com/sw/img/candidato1.png"));
        candidato2.setGraphic(new ImageView("/com/sw/img/candidato2.png"));
        candidato3.setGraphic(new ImageView("/com/sw/img/candidato3.png"));
    }

    @FXML private void accionBtnCandidato1(ActionEvent e)
    {
        notificar(0);
    }

    @FXML private void accionBtnCandidato2(ActionEvent e)
    {
        notificar(1);
    }

    @FXML private void accionBtnCandidato3(ActionEvent e)
    {
        notificar(2);
    }

    public void notificar(int candidato)
    {
        setChanged();
        notifyObservers(candidato);
    }

}
