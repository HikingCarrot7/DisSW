package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Curso;
import model.Maestro;
import model.Registro;

/**
 * @author HikingC7
 */
public class DAORegistro extends DAOGeneral<Maestro>
{
    public static final String RUTA_REGISTROS = "datos/Registros.csv";

    public DAORegistro()
    {
        super(RUTA_REGISTROS);
    }

    @Override
    public void guardarItems(ArrayList<Maestro> maestros)
    {
        String registros = "";
        for (Maestro maestro : maestros)
            for (Curso curso : maestro.getCursos())
                registros = curso.getAlumnosInscritos()
                        .stream()
                        .map((alumno) -> maestro.getClaveMaestro() + ","
                                + curso.getAsignatura().getClaveAsignatura() + ","
                                + alumno.getMatricula()
                                + System.getProperty("line.separator"))
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
