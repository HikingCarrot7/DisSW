package ejemplo;

/**
 *
 * @author Nicolás
 */
public class Cliente
{

    public static void main(String[] args)
    {
        Lampara lampara = new Lampara();
        ComandoEncenderLampara comandoApagarLampara = new ComandoEncenderLampara(lampara);
        ControlRemoto controlRemoto = new ControlRemoto(comandoApagarLampara);
        controlRemoto.press();
        controlRemoto.pressUndo();
    }
}
