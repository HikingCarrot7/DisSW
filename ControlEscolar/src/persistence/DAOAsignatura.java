package persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.stream.Collectors;
import model.Asignatura;

/**
 * @author HikingC7
 */
public class DAOAsignatura extends DAOGeneral<ArrayList<Asignatura>, ArrayList<Asignatura>>
{

    public static final String RUTA_ASIGNATURAS = "datos/Asignaturas.csv";

    public DAOAsignatura()
    {
        super(RUTA_ASIGNATURAS);
    }

    @Override
    public void saveData(ArrayList<Asignatura> asignaturas)
    {
        String datosAsignaturas = "";

        datosAsignaturas = asignaturas
                .stream()
                .map((asignatura) -> asignatura + SALTO_LINEA)
                .reduce(datosAsignaturas, String::concat);

        try (Formatter out = new Formatter(new FileWriter(FILE)))
        {
            out.format("%s", datosAsignaturas);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Asignatura> loadData()
    {
        try
        {
            return Files.lines(Paths.get(RUTA_ASIGNATURAS))
                    .map(line -> line.split(","))
                    .map(data -> new Asignatura(Integer.parseInt(data[0]), data[1], data[2]))
                    .collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        return new ArrayList<>();
    }
}
