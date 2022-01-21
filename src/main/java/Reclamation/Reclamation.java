
package Reclamation;

import Contrat.TauxAssurable;
import Contrat.PoliceSoin;
import Dollar.Dollar;
import Outils.DateConteneur;

public class Reclamation {

    private Dollar montantRemboursement;
    private Dollar montantReclamation;

    private DateConteneur dateConteneurCalcul;
    private DateConteneur dateConteneurReclamation;

    private PoliceSoin policeSoin;
    private TauxAssurable tauxAssurable;

    public Reclamation() {
    }


    public Reclamation(DateConteneur dateConteneurReclamation,
                       DateConteneur dateConteneurCalcul,
                       Dollar montantReclamation,
                       Dollar montantRemboursement,
                       PoliceSoin policeSoin,
                       TauxAssurable tauxAssurable) {
        this.dateConteneurCalcul = dateConteneurCalcul;
        this.dateConteneurReclamation = dateConteneurReclamation;
        this.montantReclamation = montantReclamation;
        this.montantRemboursement = montantRemboursement;
        this.policeSoin = policeSoin;
        this.tauxAssurable = tauxAssurable;
    }

    public Dollar getMontantRemboursement() { return this.montantRemboursement; }
    public Dollar getMontantReclamation() { return this.montantReclamation; }
    public DateConteneur getDateCalcul() { return dateConteneurCalcul; }
    public DateConteneur getDateReclamation() { return dateConteneurReclamation; }
    public PoliceSoin getPoliceSoin() { return policeSoin; }
    public TauxAssurable getContratSoin() { return tauxAssurable; }

    //SET
    public void setMontantRemboursement(Dollar montantRemboursement) {
        this.montantRemboursement = montantRemboursement;
    }
    public void setMontantReclamation(Dollar montantReclamation) {
        this.montantReclamation = montantReclamation;
    }
    public void setDateCalcul(DateConteneur dateConteneurCalcul) {
        this.dateConteneurCalcul = dateConteneurCalcul;
    }
    public void setDateReclamation(DateConteneur dateConteneurReclamation) {
        this.dateConteneurReclamation = dateConteneurReclamation;
    }
    public void setPoliceSoin(PoliceSoin policeSoin) {
        this.policeSoin = policeSoin;
    }
    public void setTauxAssurable(TauxAssurable tauxAssurable) {
        this.tauxAssurable = tauxAssurable;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "montantRemboursement=" + montantRemboursement +
                ", montantReclamation=" + montantReclamation +
                ", dateCalcul=" + dateConteneurCalcul +
                ", dateReclamation=" + dateConteneurReclamation +
                ", policeSoin=" + policeSoin +
                ", contratSoin=" + policeSoin.getCategorieSoin().name() +
                '}';
    }
}
