
package DecodeurJSON;

import CheminFichier.CheminFichierFactory;
import Client.Client;
import Client.Clients;
import Contrat.Contrat;
import Contrat.DictionnaireSoin;
import Contrat.PoliceSoin;
import Dollar.DollarFactory;
import Erreur.ErreurSortie;
import Fichier.FichierEcrire;
import Fichier.FichierLire;
import Fichier.FichierTemps;
import Outils.DateConteneur;
import Outils.DateFactory;
import Reclamation.Reclamation;
import Reclamation.ReclamationsMensuelle;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class Database {

    //VARIABLES

    private static final String CLIENTS = "clients";
    private static final String NO_CLIENT = "numeroClient";
    private static final String NO_SOIN = "numeroSoin";
    private static final String CONTRAT = "contrat";
    private static final String RECLAMATIONS_MENSUELLES = "reclamationsMensuelles";
    private static final String DATE_MOIS = "dateMois";
    private static final String DATE_CALCUL = "dateCalcul";
    private static final String DATE_REMB = "dateReclamation";
    private static final String RECLAMATION = "reclamations";
    private static final String MONTANT_REMB = "montantRemboursement";
    private static final String MONTANT_RECL = "montantReclamation";

    //PUBLIC

    public static Clients formaterImport(String stringJson){
        Clients clients = new Clients();
        if (FichierLire.fichierExiste(CheminFichierFactory.getSource().getChemin())) {
            FichierTemps fichierTempsData = new FichierTemps( new File(CheminFichierFactory.getSource().getChemin()) );
            if (fichierTempsData.estInchange()) clients = getClients(stringJson, clients);
        } else {
            ErreurSortie.erreur("database.nouveau");
        }
        return clients;
    }

    private static Clients getClients(String stringJson, Clients clients) {
        try {
            JSONObject jsonObjectClients = new JSONObject(stringJson);
            ajouterClients(clients, jsonObjectClients);
        } catch (Exception e) {
            clients = new Clients();
            ErreurSortie.erreur("database.lecture");
        }
        return clients;
    }

    public static String formaterExport(Clients clients) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CLIENTS, getJSONArrayClients(clients));

        return jsonObject.toString(4);
    }


    //PRIVATE IMPORT

    private static void ajouterClients(Clients clients, JSONObject jsonObjectClients) {
        JSONArray jsonArrayClients = jsonObjectClients.getJSONArray(CLIENTS);
        for (int i = 0; i < jsonArrayClients.length(); i++) {
            JSONObject jsonObjectClient = jsonArrayClients.getJSONObject(i);
            Client client = new Client(
                    String.valueOf(jsonObjectClient.get(NO_CLIENT)),
                    Contrat.obtenirParNom(String.valueOf(jsonObjectClient.get(CONTRAT))),
                    new ArrayList<ReclamationsMensuelle>()
            );
            ajouterReclamationsMensuelle(jsonObjectClient, client);

            clients.add(client);
        }
    }

    private static void ajouterReclamationsMensuelle(JSONObject jsonObjectClient, Client client) {
        JSONArray jsonArrayReclamationsMensuelle = jsonObjectClient.getJSONArray(RECLAMATIONS_MENSUELLES);
        for (int j = 0; j < jsonArrayReclamationsMensuelle.length(); j++) {
            JSONObject jsonObjReclamationsMensuelle = jsonArrayReclamationsMensuelle.getJSONObject(j);
            ReclamationsMensuelle reclamationsMensuelle = new ReclamationsMensuelle(
                    DateFactory.obtenir( String.valueOf(jsonObjReclamationsMensuelle.get(DATE_MOIS)) ),
                    new ArrayList<Reclamation>()
            );
            ajouterReclamations(client, reclamationsMensuelle, jsonObjReclamationsMensuelle);
            client.ajouterReclamationsMensuelle(reclamationsMensuelle);
        }
    }

    private static void ajouterReclamations(Client client,
                                            ReclamationsMensuelle reclamationsMensuelle,
                                            JSONObject jsonObjReclamationsMensuelle) {
        JSONArray jsonArrayReclamation = jsonObjReclamationsMensuelle.getJSONArray(RECLAMATION);
        for (int k = 0; k < jsonArrayReclamation.length(); k++) {
            JSONObject jsonObjectReclamation = jsonArrayReclamation.getJSONObject(k);
            Reclamation reclamation = getReclamation(client.getContrat(), jsonObjectReclamation);
            reclamationsMensuelle.ajouterReclamation(reclamation);
        }
    }

    private static Reclamation getReclamation(Contrat contrat, JSONObject jsonObjReclamation) {
        Reclamation reclamation = new Reclamation();
        reclamation.setDateCalcul(DateFactory.obtenir( String.valueOf(jsonObjReclamation.get(DATE_CALCUL))));
        reclamation.setDateReclamation(DateFactory.obtenir( String.valueOf(jsonObjReclamation.get(DATE_REMB))));
        reclamation.setMontantRemboursement(DollarFactory.getDollar( Integer.parseInt( String.valueOf(jsonObjReclamation.get(MONTANT_REMB))))); //DEVISE
        reclamation.setMontantReclamation(DollarFactory.getDollar( Integer.parseInt( String.valueOf(jsonObjReclamation.get(MONTANT_RECL))))); //DEVISE
        reclamation.setPoliceSoin(new PoliceSoin(contrat, Integer.parseInt(String.valueOf(jsonObjReclamation.get(NO_SOIN)))));
        reclamation.setTauxAssurable(DictionnaireSoin.getTauxAssurable(reclamation.getPoliceSoin()));
        return reclamation;
    }


    //PRIVATE EXPORT

    private static JSONArray getJSONArrayClients(Clients clients) {
        JSONArray jsonArrayClients = new JSONArray();
        for ( Client client : clients ) {
            JSONObject jsonObjectClient = new JSONObject(); {
                jsonObjectClient.put(CONTRAT,       client.getContrat().toString());
                jsonObjectClient.put(NO_CLIENT,     client.getNumeroClient());
                jsonObjectClient.put(RECLAMATIONS_MENSUELLES,       getJSONArrayReclamationsMensuelle(client));
            }
            jsonArrayClients.put(jsonObjectClient);
        }
        return jsonArrayClients;
    }

    private static JSONArray getJSONArrayReclamationsMensuelle(Client client) {
        JSONArray jsonArrayReclamationsMensuelle = new JSONArray();
        for ( ReclamationsMensuelle reclamationsMensuelle : client.getReclamationsMensuelles() ) {
            JSONObject jsonObjectReclamationsMensuelle = new JSONObject(); {
                jsonObjectReclamationsMensuelle.put(DATE_MOIS,      reclamationsMensuelle.getDateMois().toStringClient());
                jsonObjectReclamationsMensuelle.put(RECLAMATION,    getJSONArrayReclamations(reclamationsMensuelle));
            }
            jsonArrayReclamationsMensuelle.put(jsonObjectReclamationsMensuelle);
        }
        return jsonArrayReclamationsMensuelle;
    }

    private static JSONArray getJSONArrayReclamations(ReclamationsMensuelle reclamationsMensuelle) {
        JSONArray jsonArrayReclamation = new JSONArray();
        for ( Reclamation reclamation : reclamationsMensuelle.getReclamations() ) {
            JSONObject jsonObjectReclamation = new JSONObject(); {
                jsonObjectReclamation.put(MONTANT_REMB,     reclamation.getMontantRemboursement().getCents());
                jsonObjectReclamation.put(MONTANT_RECL,     reclamation.getMontantReclamation().getCents());
                jsonObjectReclamation.put(DATE_CALCUL,      reclamation.getDateCalcul().toString()); //COMPLET ?
                jsonObjectReclamation.put(DATE_REMB,        reclamation.getDateReclamation().toString());
                jsonObjectReclamation.put(NO_SOIN,          reclamation.getPoliceSoin().getNumeroSoin());
            }
            jsonArrayReclamation.put(jsonObjectReclamation);
        }
        return jsonArrayReclamation;
    }

}
