
package Contrat;

import Erreur.ErreurSortie;

import java.util.HashMap;

public class DictionnaireSoin {

    private static final HashMap<PoliceSoin, TauxAssurable> contrat = new HashMap<>() {
        {
            // PoliceSoin             -->    , TauxAssurable
            put(policeFactory(Contrat.A, 000), tauxFactory( 25,      0,      500 ));
            put(policeFactory(Contrat.A, 100), tauxFactory( 35,      0,      250 ));
            put(policeFactory(Contrat.A, 150), tauxFactory( 0,       0,      500 ));
            put(policeFactory(Contrat.A, 175), tauxFactory( 50,      0,      200 ));
            put(policeFactory(Contrat.A, 200), tauxFactory( 25,      0,      250 ));
            put(policeFactory(Contrat.A, 300), tauxFactory( 0,       0,      500 ));
            put(policeFactory(Contrat.A, 400), tauxFactory( 0,       0,      500 ));
            put(policeFactory(Contrat.A, 500), tauxFactory( 25,      0,      150 ));
            put(policeFactory(Contrat.A, 600), tauxFactory( 40,      0,      300 ));
            put(policeFactory(Contrat.A, 700), tauxFactory( 25,      0,      500 ));

            put(policeFactory(Contrat.B, 000), tauxFactory( 50,      40,     500 ));
            put(policeFactory(Contrat.B, 100), tauxFactory( 50,      50,     250 ));
            put(policeFactory(Contrat.B, 150), tauxFactory( 0,       0,      500 ));
            put(policeFactory(Contrat.B, 175), tauxFactory( 75,      0,      200 ));
            put(policeFactory(Contrat.B, 200), tauxFactory( 100,     70,     250 ));
            put(policeFactory(Contrat.B, 300), tauxFactory( 50,      0,      500 ));
            put(policeFactory(Contrat.B, 400), tauxFactory( 0,       0,      500 ));
            put(policeFactory(Contrat.B, 500), tauxFactory( 50,      50,     150 ));
            put(policeFactory(Contrat.B, 600), tauxFactory( 100,     0,      300 ));
            put(policeFactory(Contrat.B, 700), tauxFactory( 70,      0,      500 ));

            put(policeFactory(Contrat.C, 000), tauxFactory( 90,      0,      500 ));
            put(policeFactory(Contrat.C, 100), tauxFactory( 95,      0,      250 ));
            put(policeFactory(Contrat.C, 150), tauxFactory( 85,      0,      500 ));
            put(policeFactory(Contrat.C, 175), tauxFactory( 90,      0,      200 ));
            put(policeFactory(Contrat.C, 200), tauxFactory( 90,      0,      250 ));
            put(policeFactory(Contrat.C, 300), tauxFactory( 90,      0,      500 ));
            put(policeFactory(Contrat.C, 400), tauxFactory( 90,      0,      500 ));
            put(policeFactory(Contrat.C, 500), tauxFactory( 90,      0,      150 ));
            put(policeFactory(Contrat.C, 600), tauxFactory( 75,      0,      300 ));
            put(policeFactory(Contrat.C, 700), tauxFactory( 90,      0,      500 ));

            put(policeFactory(Contrat.D, 000), tauxFactory( 100,     85,     500 ));
            put(policeFactory(Contrat.D, 100), tauxFactory( 100,     75,     250 ));
            put(policeFactory(Contrat.D, 150), tauxFactory( 100,     150,    500 ));
            put(policeFactory(Contrat.D, 175), tauxFactory( 95,      0,      200 ));
            put(policeFactory(Contrat.D, 200), tauxFactory( 100,     100,    250 ));
            put(policeFactory(Contrat.D, 300), tauxFactory( 100,     0,      500 ));
            put(policeFactory(Contrat.D, 400), tauxFactory( 100,     65,     500 ));
            put(policeFactory(Contrat.D, 500), tauxFactory( 100,     0,      150 ));
            put(policeFactory(Contrat.D, 600), tauxFactory( 100,     100,    300 ));
            put(policeFactory(Contrat.D, 700), tauxFactory( 100,     90,     500 ));

            put(policeFactory(Contrat.E, 000), tauxFactory( 15,      0,      500 ));
            put(policeFactory(Contrat.E, 100), tauxFactory( 25,      0,      250 ));
            put(policeFactory(Contrat.E, 150), tauxFactory( 15,      0,      500 ));
            put(policeFactory(Contrat.E, 175), tauxFactory( 25,      20,     200 ));
            put(policeFactory(Contrat.E, 200), tauxFactory( 12,      0,      250 ));
            put(policeFactory(Contrat.E, 300), tauxFactory( 60,      0,      500 ));
            put(policeFactory(Contrat.E, 400), tauxFactory( 25,      15,     500 ));
            put(policeFactory(Contrat.E, 500), tauxFactory( 30,      20,     150 ));
            put(policeFactory(Contrat.E, 600), tauxFactory( 15,      0,      300 ));
            put(policeFactory(Contrat.E, 700), tauxFactory( 22,      0,      500 ));

        }
    };
    
    public static TauxAssurable getTauxAssurable(PoliceSoin policeSoin) {
        PoliceSoin policeSoinEquivalent = policeEquivalente(policeSoin);
        return contrat.getOrDefault(policeSoinEquivalent, null);
    }


// PRIVATE
    private static PoliceSoin policeFactory(Contrat contrat, int numeroDeSoin) {
        PoliceSoinFactory policeSoinFactory = new PoliceSoinFactory();
        return policeSoinFactory.obtenir(contrat, numeroDeSoin);
    }

    private static TauxAssurable tauxFactory(int remboursementPourcentage,
                                             int remboursementMaximumRencontre,
                                             int remboursementMaximumMensuel) {
        return new TauxAssurable(remboursementPourcentage, remboursementMaximumRencontre, remboursementMaximumMensuel);
    }


    public static PoliceSoin policeEquivalente(PoliceSoin policeSoin) {

        if (policeSoin == null) {
            ErreurSortie.terminer("client.soin");

        } else if (PoliceSoinFactory.numeroValide(policeSoin.getNumeroSoin(), 300, 100)) {
            PoliceSoinFactory policeSoinFactory = new PoliceSoinFactory();
            policeSoin = policeSoinFactory.obtenir(policeSoin.getCategorieSoin(), "300");
        }

        return policeSoin;
    }


}
