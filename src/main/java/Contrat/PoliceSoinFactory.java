
package Contrat;

import Erreur.ErreurSortie;
import Outils.StringAnalyse;

public class PoliceSoinFactory {

    public PoliceSoinFactory() {
    }

    public static PoliceSoin obtenir(Contrat contrat, String numeroDeSoin) {

        int numeroSoinInt = verifierSoin(numeroDeSoin);
        PoliceSoin policeSoin = new PoliceSoin(contrat, numeroSoinInt);

        return policeSoin;
    }

    protected PoliceSoin obtenir(Contrat contrat, int numeroDeSoin) {
        return new PoliceSoin(contrat, numeroDeSoin);
    }

    //Protected
    private static boolean estNumeroPlageValide(int numeroSoin) {
        return numeroSoin >= 0 && numeroSoin <= 700;
    }

    //Private
    protected static boolean estNumeroValide(int numeroSoin) {

        boolean estValide = false;

        if (numeroValide(numeroSoin, 300, 100)) {
            estValide = true;
        } else {
            estValide = numeroSoin % 100 == 0 || numeroSoin == 150 || numeroSoin == 175;
        }

        return estValide;
    }

    protected static boolean numeroValide(int numeroSoin, int classeNumero, int intervale) {
        return numeroSoin >= classeNumero && numeroSoin < (classeNumero+intervale);
    }

    private static int verifierSoin(String numeroSoin) {
        int numeroSoinInt = 0;
        if (null != numeroSoin && !numeroSoin.isEmpty() && StringAnalyse.neContientQueDesChiffres(numeroSoin)) {
            numeroSoinInt = Integer.parseInt(numeroSoin);
            if (!estNumeroValide(numeroSoinInt) || !estNumeroPlageValide(numeroSoinInt)) {
                ErreurSortie.terminer("client.soin.existe");
            }
        } else {
            ErreurSortie.terminer("client.soin");
        }
        return numeroSoinInt;
    }

}
