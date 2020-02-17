package persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.stream.Collectors;
import model.Alumno;

/**
 * @author HikingC7
 */
public class DAOAlumno extends DAOGeneral<ArrayList<Alumno>, ArrayList<Alumno>>
{

    public static final String RUTA_ALUMNOS = "datos/Alumnos.csv";

    public DAOAlumno()
    {
        super(RUTA_ALUMNOS);
    }

    @Override
    public void saveData(ArrayList<Alumno> alumnos)
    {
        String datosAlumno = "";

        datosAlumno = alumnos
                .stream()
                .map((alumno) -> alumno + SALTO_LINEA)
                .reduce(datosAlumno, String::concat);

        try (Formatter out = new Formatter(new FileWriter(FILE)))
        {
            out.format("%s", datosAlumno);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public ArrayList<Alumno> loadData()
    {
        try
        {
            return Files.lines(Paths.get(RUTA_ALUMNOS))
                    .map(line -> line.split(","))
                    .map(data -> new Alumno(Integer.parseInt(data[0]), data[1], data[2]))
                    .collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        return new ArrayList<>();
    }

}
