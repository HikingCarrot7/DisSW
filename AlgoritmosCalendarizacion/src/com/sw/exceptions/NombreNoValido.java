package com.sw.exceptions;

/**
 *
 * @author HikingCarrot7
 */
public class NombreNoValido extends InputNoValido
{

    private int row;
    private int col;

    public NombreNoValido(int row, int col)
    {
        super("El nombre no es válido, se tomará el identificador como nombre ¿Continuar?", row, col);
    }

}
