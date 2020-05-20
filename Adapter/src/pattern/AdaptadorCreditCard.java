package pattern;

/**
 *
 * @author Nicolás
 */
public class AdaptadorCreditCard implements HacerPago
{

    private CreditCard creditCard;

    public AdaptadorCreditCard(CreditCard creditCard)
    {
        this.creditCard = creditCard;
    }

    @Override public void pagar(double monto)
    {
        creditCard.executePay(monto);
    }

}
