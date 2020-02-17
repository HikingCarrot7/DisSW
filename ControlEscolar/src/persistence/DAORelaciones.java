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
import model.Relacion;

/**
 * @author HikingC7
 */
public class DAORelaciones extends DAOGeneral<HashMap<Maestro, ArrayList<Curso>>, ArrayList<Relacion>>
{

    public static final String RUTA_RELACIONES = "datos/Relaciones.csv";

    public DAORelaciones()
    {
        super(RUTA_RELACIONES);
    }

    @Override
    public void saveData(HashMap<Maestro, ArrayList<Curso>> maestros)
    {
        String relaciones = "";

        for (Maestro maestro : maestros.keySet())
            relaciones = maestros.get(maestro)
                    .stream()
                    .map((curso) -> maestro.getClaveMaestro() + ","
                    + curso.getAsignatura().getClaveAsignatura() + ","
                    + curso.getInicialesCurso()
                    + SALTO_LINEA)
                    .reduce(relaciones, String::concat);

        try (Formatter out = new Formatter(new FileWriter(FILE)))
        {
            out.format("%s", relaciones);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Relacion> loadData()
    {
        try
        {
            return Files.lines(Paths.get(RUTA_RELACIONES))
                    .map(line -> line.split(","))
                    .map(data -> new Relacion(Integer.parseInt(data[0]), Integer.parseInt(data[1])))
                    .collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        return new ArrayList<>();
    }

}
