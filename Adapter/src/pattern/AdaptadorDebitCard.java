package pattern;

/**
 *
 * @author Nicolás
 */
public class AdaptadorDebitCard implements HacerPago
{

    private DebitCard debitCard;

    public AdaptadorDebitCard(DebitCard debitCard)
    {
        this.debitCard = debitCard;
    }

    @Override public void pagar(double monto)
    {
        debitCard.payment(monto);
    }

}
