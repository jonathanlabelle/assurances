
package Contrat;

import Dollar.Dollar;
import Dollar.DollarFactory;

public class TauxAssurable {

    private int pourcentageMaximum;
    private Dollar montantMaximumRencontre;
    private Dollar montantMaximumMensuel;


    // CONSTRUCTEUR
    public TauxAssurable(int pourcentageMaximum, int montantMaximumRencontre, int montantMaximumMensuel) {
        setPourcentageMaximum(pourcentageMaximum);
        setMontantMaximumRencontre(montantMaximumRencontre);
        setMontantMaximumMensuel(montantMaximumMensuel);
    }

    protected TauxAssurable(int pourcentageMaximum, int montantMaximumRencontre) {
        setPourcentageMaximum(pourcentageMaximum);
        setMontantMaximumRencontre(montantMaximumRencontre);
    }

    protected TauxAssurable(int pourcentageMaximum) {
        setPourcentageMaximum(pourcentageMaximum);
    }


    // GET
    public int getPourcentageMaximum() {
        return pourcentageMaximum;
    }

    public Dollar getMontantMaximumRencontre() {
        return montantMaximumRencontre;
    }

    public Dollar getMontantMaximumMensuel() { return montantMaximumMensuel; }


    // SET
    //TODO : DOLLARFACTORY
    private void setPourcentageMaximum(int pourcentageMaximum) {
        this.pourcentageMaximum = pourcentageMaximum;
    }

    private void setMontantMaximumRencontre(int montantMaximumRencontre) {
        this.montantMaximumRencontre = DollarFactory.getDollar((montantMaximumRencontre) * 100);
    }

    public void setMontantMaximumMensuel(int montantMaximumMensuel) {
        this.montantMaximumMensuel = DollarFactory.getDollar((montantMaximumMensuel) * 100);
    }

}
