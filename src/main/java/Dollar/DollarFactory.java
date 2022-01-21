package Dollar;

import Erreur.ErreurSortie;

public class DollarFactory {

    //PUBLIC

    public DollarFactory() {

    }

    public static Dollar getDollar(int cents) {

        Dollar dollar = null;

        if (estValide(cents))
            dollar = new Dollar(cents);
        else
            ErreurSortie.terminer("dollar.creer");

        return dollar;
    }

    public static Dollar getDollar(String centsJSON) {

        boolean negatif = estNegatif(centsJSON);

        if (negatif) {
            centsJSON = centsJSON.substring(1);
        }

        verifierDevise(centsJSON);
        StringBuilder centsString = new StringBuilder();

        if (negatif) {
            centsString.append('-');
        }

        for (int i = 0; i < centsJSON.length(); i++) {
            if (centsJSON.charAt(i) >= '0' && centsJSON.charAt(i) <= '9') {
                centsString.append(centsJSON.charAt(i));
            }
        }

        return getDollar(Integer.parseInt(centsString.toString()));
    }

    //PRIVATE

    private static boolean estValide(int cents) {
        return cents <= Dollar.getMaxValue() && cents >= Dollar.getMinValue();
    }

    private static void verifierDevise(String montantReclamation) {

        int len = montantReclamation.length();

        boolean matches = false;

        if (len <= 4 || len > 11 || len == 8) {
            ErreurSortie.terminer("dollar.string");
        }

        if (len >= 9) {
            matches = montantReclamation.matches("^(([0-9]{1,3}\\,[0-9]{3}\\.?\\d{2}?)\\$$)");
        } else if (len >= 5) {
            matches = montantReclamation.matches("^(([0-9]{1,3}\\.?\\d{2}?)\\$$)");
        }

        if (!matches) {
            ErreurSortie.terminer("dollar.string");
        }

    }

    private static boolean estNegatif(String sDollar) {
        return sDollar.charAt(0) == '-';
    }

}
