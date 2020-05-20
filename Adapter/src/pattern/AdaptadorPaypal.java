package pattern;

/**
 *
 * @author Nicolás
 */
public class AdaptadorPaypal implements HacerPago
{

    private Paypal paypal;

    public AdaptadorPaypal(Paypal paypal)
    {
        this.paypal = paypal;
    }

    @Override public void pagar(double monto)
    {
        paypal.sendPayment(monto);
    }

}
