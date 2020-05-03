package test;

public class Cliente
{

    public static void main(String args[])
    {
        String cadenaEncriptada;
        cadenaEncriptada = new Cliente().encriptar("Hola", new FactoryAlgoEncripKHash());
        System.out.println(cadenaEncriptada);
    }

    public String encriptar(String texto, FactoryAlgoEncrip factory)
    {
        AlgoEncriptamiento algo = factory.createInstance();
        return algo.encriptar(texto);
    }

}
