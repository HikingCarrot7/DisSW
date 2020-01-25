package persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.stream.Collectors;
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

        try (Formatter out = new Formatter(new FileWriter(FILE)))
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
        try
        {
            return Files.lines(Paths.get(RUTA_REGISTROS))
                    .map(line -> line.split(","))
                    .map(data -> new Registro(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])))
                    .collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        return new ArrayList<>();
    }

}
