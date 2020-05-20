package com.sw.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;

/**
 *
 * @author Nicolás
 */
public class GeneradorExcel implements Observador
{

    private final String RUTA_ARCHIVO = "votos.csv";
    private final String SALTO_LINEA = "\r\n";
    private final File file;

    public GeneradorExcel()
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

    @Override public void update(Observado observado, Object item)
    {
        actualizarDatos((int[]) item);
    }

    private void actualizarDatos(int[] votos)
    {
        String result = "";

        for (int i = 0; i < votos.length; i++)
            result += String.format("Candidato %d, Votos %d" + SALTO_LINEA, i, votos[i]);

        escribirDatos(result);
    }

    private void escribirDatos(String result)
    {
        try (Formatter out = new Formatter(file))
        {
            out.format("%s", result);

        } catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
