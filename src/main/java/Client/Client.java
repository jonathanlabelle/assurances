
package Client;

import Contrat.Contrat;
import Outils.DateConteneur;
import Reclamation.*;

import java.util.ArrayList;
import java.util.List;

public class Client {
    // Variables instances
    private Contrat contrat;
    private String numeroClient;
    private List<ReclamationsMensuelle> reclamationsMensuelles;

    //Constructeur

    //TODO : Protected
    public Client(String numeroClient, Contrat contrat, List<ReclamationsMensuelle> reclamationsMensuelles) {
        this.numeroClient = numeroClient;
        this.contrat = contrat;
        this.reclamationsMensuelles = reclamationsMensuelles;
    }

    //SET
    public void ajouterReclamationsMensuelle(ReclamationsMensuelle reclamationsMensuelle) {
        if (reclamationsMensuelles != null) {
            reclamationsMensuelles.add(reclamationsMensuelle);
        }
    }


    //GET
    public String getNumeroClient() {
        return numeroClient;
    }
    public Contrat getContrat() {
        return contrat;
    }
    public List<ReclamationsMensuelle> getReclamationsMensuelles() {
        return reclamationsMensuelles;
    }
    public ReclamationsMensuelle getReclamationsMensuelle(ReclamationsMensuelle reclamationsMensuelle) {
        for (ReclamationsMensuelle reclamationsMensuelleEx : reclamationsMensuelles) {
            if (reclamationsMensuelleEx.equals(reclamationsMensuelle)) return reclamationsMensuelleEx;
        }
        return null;
    }
    public ReclamationsMensuelle getPremiereReclamationMensuelle() {
        return reclamationsMensuelles.get(0);
    }
    public DateConteneur getDateMois() {
        if (getReclamationsMensuelles().size() != 0) {
            return getPremiereReclamationMensuelle().getDateMois();
        }
        return null;
    }



    //TO STRING

    @Override
    public String toString() {
        return String.format("%s%s", contrat, numeroClient);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Client)) return false;
        Client client = (Client) obj;
        return this.toString().equals(client.toString());
    }
}
