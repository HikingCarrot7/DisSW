package pattern;

public class Cliente
{

    public static void main(String[] args)
    {
        Decaf decaf = new Decaf();
        CremaBatida cremaBatida = new CremaBatida(decaf);
        ChispasChocolate chispasChocolate = new ChispasChocolate(cremaBatida);
        System.out.println(chispasChocolate.getCosto());
    }

}
