package controlescolar;

import java.util.Scanner;

/**
 *
 * @author HikingCarrot7
 */
public class Menu
{

    private ControlEscolar controlEscolar;
    private Scanner in;

    public Menu()
    {
        controlEscolar = new ControlEscolar();
        in = new Scanner(System.in);

    }

    public void mostrarMenu()
    {

        while (true)
        {

            mostrarOpciones();
            eligirOpcion();

        }

    }

    private void mostrarOpciones()
    {

        System.out.println("\n\n1.-Consultar maestros."
                + "\n2.-Consultar asignaturas."
                + "\n3.-Consultar alumnos."
                + "\n4.-Mostrar relaciones de maestros con asignaturas."
                + "\n5.-Mostrar todas las relaciones existentes."
                + "\n6.-Añadir a un maestro."
                + "\n7.-Añadir a un alumno."
                + "\n8.-Añadir una asignatura."
                + "\n9.-Relacionar asignatura con maestro."
                + "\n10.-Quitar relación entre asignatura y maestro."
                + "\n11.-Dar de baja a un maestro."
                + "\n12.-Dar de baja a un alumno de una asignatura."
                + "\n13.-Eliminar a un alumno."
                + "\n14.-Eliminar una asignatura."
                + "\nOpción: ");

    }

    private void eligirOpcion()
    {
        int claveMaestro;
        int matricula;
        int claveAsignatura;
        String nombreAsignatura;
        String licenciatura;
        String nombreAlumno;
        String apellidoAlumno;

        String opcion = in.nextLine();

        switch (Integer.parseInt(opcion))
        {
            case 1:
                controlEscolar.mostrarMaestros();
                break;
            case 2:
                controlEscolar.mostrarAsignaturas();
                break;
            case 3:
                controlEscolar.mostrarAlumnos();
                break;
            case 4:
                controlEscolar.mostrarRelacionesAsignaturas();
                break;
            case 5:
                controlEscolar.mostrarTodasRelaciones();
                break;
            case 6:
                claveMaestro = getDatoEntero("Inserte la clave del maestro: ");
                String nombreMaestro = getDatoString("Inserte el nombre del maestro: ");
                String apellidoMaestro = getDatoString("Inserte el apellido del maestro: ");

                controlEscolar.anadirMaestro(claveMaestro, nombreMaestro, apellidoMaestro);
                break;
            case 7:
                matricula = getDatoEntero("Inserte la matrícula del alumno: ");
                nombreAlumno = getDatoString("Inserte el nombre del alumno: ");
                apellidoAlumno = getDatoString("Inserte el apellido del alumno: ");

                controlEscolar.anadirAlumno(matricula, nombreAlumno, apellidoAlumno);
                break;
            case 8:
                claveAsignatura = getDatoEntero("Inserte la clave de la asignatura: ");
                nombreAsignatura = getDatoString("Inserte el nombre de la asignatura: ");
                licenciatura = getDatoString("Inserte la licenciatura: ");

                controlEscolar.anadirMaestro(claveAsignatura, nombreAsignatura, licenciatura);
                break;
            case 9:
                claveMaestro = getDatoEntero("Inserte la clave del maestro: ");
                claveAsignatura = getDatoEntero("Inserte la asignatura: ");

                controlEscolar.relacionarAsignatura(claveMaestro, claveAsignatura);
                break;
            case 10:
                claveMaestro = getDatoEntero("Inserte la clave del maestro: ");
                claveAsignatura = getDatoEntero("Inserte la asignatura: ");

                controlEscolar.relacionarAsignatura(claveMaestro, claveAsignatura);
                break;
            case 11:
                claveMaestro = getDatoEntero("Inserte la clave del maestro: ");
                controlEscolar.eliminarMaestro(claveMaestro);
                break;
            case 12:
                claveMaestro = getDatoEntero("Inserte la clave del maestro: ");
                claveAsignatura = getDatoEntero("Inserte la clave de la asignatura: ");
                matricula = getDatoEntero("Inserte la matrícula del alumno: ");

                controlEscolar.darBajaAlumnoDeAsignatura(claveMaestro, claveAsignatura, matricula);
                break;
            case 13:
                matricula = getDatoEntero("Inserte la matrícula del alumno: ");

                controlEscolar.eliminarAlumno(matricula);
                break;
            case 14:
                claveAsignatura = getDatoEntero("Inserte la clave de la asignatura: ");

                controlEscolar.eliminarAsignatura(claveAsignatura);
                break;
            default:
                System.out.println("La opción no es correcta.");

        }

    }

    public int getDatoEntero(String mensajeMostrar)
    {
        System.out.println(mensajeMostrar);
        int dato = in.nextInt();
        return dato;

    }

    public String getDatoString(String mensajeMostrar)
    {
        in.nextLine();
        System.out.println(mensajeMostrar);
        String dato = in.nextLine();
        return dato;

    }

}