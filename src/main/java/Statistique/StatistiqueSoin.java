/**
 * Objet contenant les statistique d'un soin
 *
 * @author Etienne Comtois
 * Code permanent: COME17029800
 * Courriel: jb591912@ens.uqam.ca
 *
 * Cours: inf2050-gr30
 * @version 2021-02-18
 *
 * Copyright (C) 2021 by Etienne Comtois, Jonathan Labelle, All right reserved.
 * Released under the terms of the GNU general Public License version 2 or later.
 */
package Statistique;

import Dollar.*;

public class StatistiqueSoin {
    private int numeroSoin;
    private int nombreSoin = 0;
    private Dollar montantMaximal = DollarFactory.getDollar(0);
    private Dollar montantMoyen = DollarFactory.getDollar(0);


    //CONSTRUCTEUR

    public StatistiqueSoin(int numeroSoin) {
        this.numeroSoin = numeroSoin;
    }


    //ADD

    public boolean ajouterSoin(Dollar montant) {

        if (montant.getCents() > this.montantMaximal.getCents()) montantMaximal = montant;

        montantMoyen = Dollar.Multiplication(this.montantMoyen, nombreSoin * 100);
        montantMoyen = Dollar.Addition(montantMoyen, montant);
        nombreSoin++;
        montantMoyen = DollarFactory.getDollar( montantMoyen.getCents() / nombreSoin );

        return true;

    }


    //SET

    public void setNombreSoin(int nombreSoin) {
        this.nombreSoin = nombreSoin;
    }

    public void setMontantMaximal(Dollar montantMaximal) {
        this.montantMaximal = montantMaximal;
    }

    public void setMontantMoyen(Dollar montantMoyen) {
        this.montantMoyen = montantMoyen;
    }


    //GET

    public int getNumeroSoin() {
        return numeroSoin;
    }

    public int getNombreSoin() {
        return nombreSoin;
    }

    public Dollar getMontantMaximal() {
        return montantMaximal;
    }

    public Dollar getMontantMoyen() {
        return montantMoyen;
    }
}
