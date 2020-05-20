package pattern;

public class Cliente
{

    public static void main(String[] args)
    {
        Cliente c = new Cliente();
        c.realizarPago(new AdaptadorDebitCard(new DebitCard()), 500);
        c.realizarPago(new AdaptadorCreditCard(new CreditCard()), 1500);
        c.realizarPago(new AdaptadorPaypal(new Paypal()), 5000);
    }

    public void realizarPago(HacerPago hacerPago, double monto)
    {
        hacerPago.pagar(monto);
    }

}
