
package Contrat;

import Outils.StringAnalyse;

public enum Contrat {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4);

    int number;

    Contrat(int no) {
        number = no;
    }

    public static Contrat obtenirParNom(String str) {

        for (Contrat contrat : Contrat.values()) {

            if (contrat.name().equals(str))
                return contrat;
        }

        return null;
    }

    public static boolean existe(String str) {

        Contrat contrat = Contrat.obtenirParNom(str);

        if (contrat != null) {
            return true;
        }

        return false;
    }

}
