
package DecodeurJSON.FauxClient;

public class FauxReclamation {

    private String montantReclamation;
    private String dateReclamation;
    private String soin;

    //CONSTRUCTEURS
    public FauxReclamation() {
    }

    public FauxReclamation(String montantReclamation, String dateReclamation, String soin) {
        setMontantReclamation(montantReclamation);
        setDateReclamation(dateReclamation);
        setSoin(soin);
    }


    //SET
    public void setMontantReclamation(String montantReclamation) { this.montantReclamation = montantReclamation; }
    public void setDateReclamation(String dateReclamation) { this.dateReclamation = dateReclamation; }
    public void setSoin(String soin) { this.soin = soin; }

    //GET
    public String getMontantReclamation() { return montantReclamation; }
    public String getDateReclamation() { return dateReclamation; }
    public String getSoin() { return soin; }

    @Override
    public String toString() {
        return "ReclamationStringJson{" +
                "montantReclamation='" + montantReclamation + '\'' +
                ", dateReclamation='" + dateReclamation + '\'' +
                ", soin='" + soin + '\'' +
                '}';
    }
}
