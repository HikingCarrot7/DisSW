package com.sw.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author Nicolás
 */
public class DAO implements Observador
{

    private final String RUTA_ARCHIVO = "votos.csv";
    private final String SALTO_LINEA = "\r\n";
    private final File file;

    public DAO()
    {
        this.file = new File(RUTA_ARCHIVO);

        if (!file.exists())
            try
            {
                file.createNewFile();
            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }
    }

    @Override
    public void update(Observado observado, Object item)
    {
        actualizarDatos((Candidato) item);
    }

    private void actualizarDatos(Candidato candidato)
    {
        ArrayList<String> lista = leerDatos();
        lista.set(candidato.getnCandidato(), String.format("Candidato %d, Votos %d", candidato.getnCandidato() + 1, candidato.getnVotos()));
        escribirDatos(lista);
    }

    private void escribirDatos(ArrayList<String> lista)
    {
        String result = "";
        result = lista.stream().map(linea -> linea + SALTO_LINEA).reduce(result, String::concat);

        try (Formatter out = new Formatter(file))
        {
            out.format("%s", result);

        } catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private ArrayList<String> leerDatos()
    {
        try (Scanner in = new Scanner(file))
        {
            ArrayList<String> lista = new ArrayList<>();

            while (in.hasNext())
                lista.add(in.nextLine());

            return lista;

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return null;
    }

}
