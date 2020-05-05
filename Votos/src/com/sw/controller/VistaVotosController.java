package com.sw.controller;

import com.sw.model.Candidato;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author Nicolás
 */
public class VistaVotosController extends Observable implements Initializable
{

    @FXML private Button btnCandidato1;
    @FXML private Button btnCandidato2;
    @FXML private Button btnCandidato3;
    @FXML private Label votosCandidato1;
    @FXML private Label votosCandidato2;
    @FXML private Label votosCandidato3;

    private Candidato candidato1;
    private Candidato candidato2;
    private Candidato candidato3;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        btnCandidato1.setGraphic(new ImageView("/com/sw/img/candidato1.png"));
        btnCandidato2.setGraphic(new ImageView("/com/sw/img/candidato2.png"));
        btnCandidato3.setGraphic(new ImageView("/com/sw/img/candidato3.png"));

        candidato1 = new Candidato("Candidato 1", 0, 0);
        candidato2 = new Candidato("Candidato 2", 1, 0);
        candidato3 = new Candidato("Candidato 3", 2, 0);
    }

    @FXML private void accionBtnCandidato1(ActionEvent e)
    {
        actualizarVotos(votosCandidato1, candidato1);
        notificar(candidato1);
    }

    @FXML private void accionBtnCandidato2(ActionEvent e)
    {
        actualizarVotos(votosCandidato2, candidato2);
        notificar(candidato2);
    }

    @FXML private void accionBtnCandidato3(ActionEvent e)
    {
        actualizarVotos(votosCandidato3, candidato3);
        notificar(candidato3);
    }

    public void actualizarVotos(Label labelVotos, Candidato candidato)
    {
        candidato.aumentarVotos();
        labelVotos.setText(String.format("%d votos", candidato.getnVotos()));
    }

    public void notificar(Candidato candidato)
    {
        setChanged();
        notifyObservers(candidato);
    }

}
