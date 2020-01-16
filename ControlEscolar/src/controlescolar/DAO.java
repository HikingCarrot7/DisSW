package controlescolar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author HikingCarrot7
 */
public class DAO
{

    public static final String RUTA_MAESTROS = "datos/Maestros.csv";
    public static final String RUTA_ALUMNOS = "datos/Alumnos.csv";
    public static final String RUTA_ASIGNATURAS = "datos/Asignaturas.csv";
    public static final String RUTA_RELACIONES = "datos/Relaciones.csv";
    public static final String RUTA_REGISTROS = "datos/Registros.csv";

    private File file;

    public DAO(String ruta)
    {

        file = new File(ruta);

        if (!file.exists())
            try
            {
                file.createNewFile();

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

    }

    public ArrayList<Maestro> obtenerMaestros()
    {

        ArrayList<Maestro> maestros = new ArrayList<>();

        try (Scanner in = new Scanner(new FileReader(file)))
        {

            while (in.hasNext())
            {

                String[] datos = in.nextLine().split(",");

                int claveMaestro = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String apellido = datos[2];

                maestros.add(new Maestro(claveMaestro, nombre, apellido));

            }

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return maestros;

    }

    public void guardarMaestros(ArrayList<Maestro> maestros)
    {

        String datosMaestros = "";

        datosMaestros = maestros
                .stream()
                .map((maestro) -> maestro + System.getProperty("line.separator"))
                .reduce(datosMaestros, String::concat);

        try (Formatter out = new Formatter(new FileWriter(file)))
        {

            out.format("%s", datosMaestros);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<Alumno> obtenerAlumnos()
    {

        ArrayList<Alumno> alumnos = new ArrayList<>();

        try (Scanner in = new Scanner(new FileReader(file)))
        {

            while (in.hasNext())
            {

                String[] datos = in.nextLine().split(",");

                int matricula = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String apellido = datos[2];

                alumnos.add(new Alumno(matricula, nombre, apellido));

            }

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return alumnos;

    }

    public void guardarAlumnos(ArrayList<Alumno> alumnos)
    {

        String datosAlumno = "";

        datosAlumno = alumnos
                .stream()
                .map((alumno) -> alumno + System.getProperty("line.separator"))
                .reduce(datosAlumno, String::concat);

        try (Formatter out = new Formatter(new FileWriter(file)))
        {

            out.format("%s", datosAlumno);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<Asignatura> obtenerAsignaturas()
    {

        ArrayList<Asignatura> asignaturas = new ArrayList<>();

        try (Scanner in = new Scanner(new FileReader(file)))
        {

            while (in.hasNext())
            {

                String[] datos = in.nextLine().split(",");

                int clave = Integer.parseInt(datos[0]);
                String nombreAsignatura = datos[1];
                String licenciatura = datos[2];

                asignaturas.add(new Asignatura(clave, nombreAsignatura, licenciatura));

            }

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return asignaturas;

    }

    public void guardarAsignaturas(ArrayList<Asignatura> asignaturas)
    {

        String datosAsignaturas = "";

        datosAsignaturas = asignaturas
                .stream()
                .map((asignatura) -> asignatura + System.getProperty("line.separator"))
                .reduce(datosAsignaturas, String::concat);

        try (Formatter out = new Formatter(new FileWriter(file)))
        {

            out.format("%s", datosAsignaturas);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public void guadarRelacionDeMaestroConAsignatura(Relacion relacion)
    {

        try (Formatter out = new Formatter(new FileWriter(file, true)))
        {

            out.format("%s", relacion + System.getProperty("line.separator"));

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public void guardarRelacionesDeMaestrosConAsignaturas(ArrayList<Maestro> maestros)
    {

        String relacionesDeMaestrosConAsignaturas = "";

        for (Maestro maestro : maestros)
            relacionesDeMaestrosConAsignaturas = maestro.getAsignaturas()
                    .stream()
                    .map((asignatura) -> maestro.getClaveMaestro() + ","
                    + asignatura.getClaveAsignatura()
                    + System.getProperty("line.separator"))
                    .reduce(relacionesDeMaestrosConAsignaturas, String::concat);

        try (Formatter out = new Formatter(new FileWriter(file)))
        {

            out.format("%s", relacionesDeMaestrosConAsignaturas);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<Relacion> obtenerRelacionesDeMaestrosConAsignaturas()
    {

        ArrayList<Relacion> relacionesDeMaestrosConAsignaturas = new ArrayList<>();

        try (Scanner in = new Scanner(new FileReader(file)))
        {

            while (in.hasNext())
            {

                String[] relacion = in.nextLine().split(",");

                int claveMaestro = Integer.parseInt(relacion[0]);
                int claveAsignatura = Integer.parseInt(relacion[1]);

                relacionesDeMaestrosConAsignaturas.add(new Relacion(claveMaestro, claveAsignatura));

            }

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return relacionesDeMaestrosConAsignaturas;

    }

    public void guardarRegistros(ArrayList<Maestro> maestros)
    {

        String registros = "";

        for (Maestro maestro : maestros)
            for (Asignatura asignatura : maestro.getAsignaturas())
                registros = asignatura.getAlumnos()
                        .stream()
                        .map((alumno) -> maestro.getClaveMaestro() + ","
                        + asignatura.getClaveAsignatura() + ","
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

    public ArrayList<Registro> obtenerRegistros()
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
