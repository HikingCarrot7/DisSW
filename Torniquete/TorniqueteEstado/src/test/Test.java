package test;

import dominio.Dispositivo;
import dominio.Estado;
import dominio.MEF;

/**
 *
 * @author Nicolás
 */
public class Test
{

    public static void main(String[] args)
    {
        MEF mef = new MEF(new Dispositivo());
        mef.setEstadoActual(Estado.ESTADO_BLOQUEADO);
        mef.pasar();
        mef.pasar();
        mef.pasar();
        mef.moneda();
        mef.listo();
        mef.moneda();
        mef.moneda();
        mef.moneda();
        mef.pasar();
    }

}
