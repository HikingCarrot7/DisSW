package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import model.Curso;
import model.Maestro;
import model.Registro;

/**
 * @author HikingC7
 */
public class DAORegistros extends DAO<HashMap<Maestro, ArrayList<Curso>>>
{

    public static final String RUTA_REGISTROS = "datos/Registros.csv";

    public DAORegistros()
    {
        super(RUTA_REGISTROS);
    }

    @Override
    public void guardarItems(HashMap<Maestro, ArrayList<Curso>> maestros)
    {
        String registros = "";

        for (Maestro maestro : maestros.keySet())
            for (Curso curso : maestros.get(maestro))
                registros = curso.getAlumnosInscritos()
                        .stream()
                        .map((alumno) -> maestro.getClaveMaestro() + ","
                        + curso.getAsignatura().getClaveAsignatura() + ","
                        + alumno.getMatricula()
                        + SALTO_LINEA)
                        .reduce(registros, String::concat);

        try (Formatter out = new Formatter(new FileWriter(file)))
        {
            out.format("%s", registros);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public ArrayList<Registro> obtenerItems()
    {
        ArrayList<Registro> registros = new ArrayList<>();

        try (Scanner in = new Scanner(new FileReader(file)))
        {
            while (in.hasNext())
            {
                String[] registro = in.nextLine().split(",");
                int claveMaestro = Integer.parseInt(registro[0]);
                int claveAsignatura = Integer.parseInt(registro[1]);
                int maticula = Integer.parseInt(registro[2]);

                registros.add(new Registro(claveMaestro, claveAsignatura, maticula));
            }

        } catch (FileNotFoundException | NoSuchElementException ex)
        {
        }

        return registros;
    }

}
