package com.sw.controller;

import com.sw.model.Candidato;
import com.sw.model.Caretaker;
import com.sw.model.Command;
import com.sw.model.Observado;
import com.sw.model.Originator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Nicolás
 */
public class VistaVotosController extends Observado implements Initializable, Controller
{

    @FXML private Button btnCandidato1;
    @FXML private Button btnCandidato2;
    @FXML private Button btnCandidato3;
    @FXML private Button undo;
    @FXML private Button redo;
    @FXML private Label votosCandidato1;
    @FXML private Label votosCandidato2;
    @FXML private Label votosCandidato3;

    private Stage stage;
    private Candidato[] candidatos;

    private Command commandVotarCandidato1;
    private Command commandVotarCandidato2;
    private Command commandVotarCandidato3;

    private Originator originator;
    private Caretaker caretaker;

    private int estadoActual;

    @Override public void initialize(URL url, ResourceBundle rb)
    {
        btnCandidato1.setGraphic(new ImageView("/com/sw/img/candidato1.png"));
        btnCandidato2.setGraphic(new ImageView("/com/sw/img/candidato2.png"));
        btnCandidato3.setGraphic(new ImageView("/com/sw/img/candidato3.png"));

        undo.setGraphic(new ImageView("/com/sw/img/undo.png"));
        redo.setGraphic(new ImageView("/com/sw/img/redo.png"));

        candidatos = new Candidato[3];

        for (int i = 0; i < candidatos.length; i++)
            candidatos[i] = new Candidato("Candidato " + (i + 1), i, 0);

        originator = new Originator();
        caretaker = new Caretaker();

        guardarEstadoActual();
        estadoActual++;

        undo.setDisable(true);
        redo.setDisable(true);

        commandVotarCandidato1 = () ->
        {
            candidatos[0].aumentarVotos();
            anadirNuevoEstado();
        };

        commandVotarCandidato2 = () ->
        {
            candidatos[1].aumentarVotos();
            anadirNuevoEstado();
        };

        commandVotarCandidato3 = () ->
        {
            candidatos[2].aumentarVotos();
            anadirNuevoEstado();
        };
    }

    @Override public void initStage(Stage stage)
    {
        this.stage = stage;
        stage.addEventFilter(KeyEvent.KEY_PRESSED, this::manejarControlZ);
    }

    @FXML private void accionBtnCandidato1(ActionEvent e)
    {
        commandVotarCandidato1.execute();
    }

    @FXML private void accionBtnCandidato2(ActionEvent e)
    {
        commandVotarCandidato2.execute();
    }

    @FXML private void accionBtnCandidato3(ActionEvent e)
    {
        commandVotarCandidato3.execute();
    }

    @FXML private void accionBtnUndo(ActionEvent e)
    {
        restaurarDesdeUnEstado(--estadoActual);
    }

    @FXML private void accionBtnRedo(ActionEvent e)
    {
        restaurarDesdeUnEstado(++estadoActual);
    }

    private void anadirNuevoEstado()
    {
        guardarEstadoActual();
        notificarVotos();
        estadoActual++;
    }

    private void manejarControlZ(KeyEvent e)
    {
        if (e.isControlDown() && e.getCode().equals(KeyCode.Z))
            if (estadoActual != 1)
                restaurarDesdeUnEstado(--estadoActual);
    }

    public void notificarVotos()
    {
        int[] votos = new int[Candidato.NUM_CANDIDATOS];

        for (int i = 0; i < votos.length; i++)
            votos[i] = candidatos[i].getnVotos();

        notificarObservadores(votos);
        actualizarCasillasVotos();
    }

    private void guardarEstadoActual()
    {
        int[] votosActuales = new int[Candidato.NUM_CANDIDATOS];

        for (int i = 0; i < votosActuales.length; i++)
            votosActuales[i] = candidatos[i].getnVotos();

        originator.setActualState(votosActuales);
        caretaker.addMemento(originator.storeInMemento());

        while (caretaker.getSavedStates().size() > (estadoActual + 1))
            caretaker.getSavedStates().remove(estadoActual);

        undo.setDisable(false);
        redo.setDisable(true);
    }

    private void restaurarDesdeUnEstado(int idxEstado)
    {
        int[] votos = caretaker.getMemento(idxEstado - 1).getSavedState();

        for (int i = 0; i < candidatos.length; i++)
            candidatos[i].setnVotos(votos[i]);

        originator.setActualState(votos);
        notificarVotos();

        undo.setDisable(idxEstado == 1);
        redo.setDisable(idxEstado == caretaker.getSavedStates().size());
    }

    private void actualizarCasillasVotos()
    {
        votosCandidato1.setText(String.format("%d votos", candidatos[0].getnVotos()));
        votosCandidato2.setText(String.format("%d votos", candidatos[1].getnVotos()));
        votosCandidato3.setText(String.format("%d votos", candidatos[2].getnVotos()));
    }

}
