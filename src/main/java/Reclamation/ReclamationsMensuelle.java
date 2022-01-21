
package Reclamation;
import Dollar.Dollar;
import Dollar.DollarFactory;
import Outils.DateConteneur;
import java.util.List;

public class ReclamationsMensuelle {

    private DateConteneur dateMois;
    private List<Reclamation> reclamations;

    public ReclamationsMensuelle(DateConteneur dateMois, List<Reclamation> reclamations) {
        setDateMois(dateMois);
        setReclamations(reclamations);
    }

    //SET

    public void setDateMois(DateConteneur dateMois) {
        this.dateMois = dateMois;
    }
    public void setReclamations(List<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }
    public void ajouterReclamation(Reclamation reclamation) {
        if (reclamations != null) {
            reclamations.add(reclamation);
        }
    }


    //GET
    public DateConteneur getDateMois() {
        return dateMois;
    }

    public List<Reclamation> getReclamations() {
        return reclamations;
    }

    public Dollar getRemboursementTotal() {
        Dollar total = DollarFactory.getDollar(0);

        for (Reclamation reclamation : this.getReclamations()) {
            total = Dollar.Addition(total, reclamation.getMontantRemboursement());
        }

        return total;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ReclamationsMensuelle)) return false;
        ReclamationsMensuelle reclamationsMensuelle = (ReclamationsMensuelle) obj;
        return dateMois.memeAnneMois(reclamationsMensuelle.dateMois);
    }

}
