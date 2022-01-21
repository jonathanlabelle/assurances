
package Client;

import Erreur.ErreurSortie;
import Reclamation.Reclamation;
import Reclamation.ReclamationsMensuelle;

import java.util.ArrayList;

public class Clients extends ArrayList<Client> {

    public boolean add(Client client) {
        ajouterExiste(client);
        ajouterExistePas(client);
        return true;
    }

    public Client getClient(Client client) {
        for (Client clientIn : this) {
            if (clientIn.equals(client)) return clientIn;
        }
        return null;
    }

    public boolean exsite(Client client) {
        return null != getClient(client);
    }

    //PRIVATE

    private void ajouterExistePas(Client client) {
        if (!this.exsite(client)) super.add(client);
    }

    private void ajouterExiste(Client client) {
        Client courrant = this.getClient(client);
        if (null != courrant) {
            for (ReclamationsMensuelle reclamationsMensuelle : client.getReclamationsMensuelles()) {
                integrerReclamationMensuelles(courrant, reclamationsMensuelle);
            }
        }
    }

    private void integrerReclamationMensuelles(Client courrant, ReclamationsMensuelle reclamationsMensuelle) {
        ReclamationsMensuelle reclamationsMensuelleEx = courrant.getReclamationsMensuelle(reclamationsMensuelle);
        if (null == reclamationsMensuelleEx) {
            courrant.ajouterReclamationsMensuelle(reclamationsMensuelle);
        } else {
            ErreurSortie.erreur("calcul.mois");
            for (Reclamation reclamation : reclamationsMensuelle.getReclamations()) {
                reclamationsMensuelleEx.ajouterReclamation(reclamation);
            }
        }
    }

}
