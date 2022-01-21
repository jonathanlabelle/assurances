
package DecodeurJSON.FauxClient;

import java.util.ArrayList;
import java.util.List;

public class FauxClient {

    private String dossier;
    private String numeroClient;
    private String contrat;
    private String dateMois;
    private List<FauxReclamation> reclamations;

    //CONSTRUCTEURS
    public FauxClient(){
        reclamations = new ArrayList<>();
    }

    public FauxClient(String numeroClient, String dateMois, String contrat, List<FauxReclamation> reclamations) {
        setNumeroClient(numeroClient);
        setDateMois(dateMois);
        setContrat(contrat);
        setReclamations(reclamations);
    }

    //SET
    public void setDossier(String dossier) {this.dossier = dossier; }
    public void setNumeroClient(String numeroClient) { this.numeroClient = numeroClient; }
    public void setDateMois(String dateMois) { this.dateMois = dateMois; }
    public void setContrat(String contrat) { this.contrat = contrat; }
    public void setReclamations(List<FauxReclamation> reclamations) { this.reclamations = reclamations; }
    public void ajouterReclamation(FauxReclamation fauxReclamation) { reclamations.add(fauxReclamation); }

    //GET
    public String getNumeroDossier() { return dossier; }
    public String getNumeroClient() { return numeroClient; }
    public String getDateMois() { return dateMois; }
    public String getContrat() { return contrat; }
    public List<FauxReclamation> getReclamations() { return reclamations; }
    public String getDossier() { return dossier; }

    //TO STRING
    @Override
    public String toString() {
        return "ClientStringJson{" +
                "dossier='" + dossier + '\'' +
                ", numeroClient='" + numeroClient + '\'' +
                ", dateMois='" + dateMois + '\'' +
                ", contrat='" + contrat + '\'' +
                ", reclamations=" + reclamations +
                '}';
    }
}