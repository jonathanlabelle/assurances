
package DecodeurJSON;

import Client.Client;
import DecodeurJSON.FauxClient.FauxClient;
import DecodeurJSON.FauxClient.FauxReclamation;
import Erreur.ErreurSortie;
import Reclamation.Reclamation;
import Statistique.Statistiques;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.*;

public class JsonDecodeur {

    //CLIENT
    private static final String CLIENT = "client";
    private static final String CONTRAT = "contrat";
    private static final String MOIS = "mois";
    private static final String DOSSIER = "dossier";

    private static final String REMBOURSEMENT = "remboursements";
    private static final String RECLAMATIONS = "reclamations";

    //REMBOURSEMENTS / RECLAMATIONS
    private static final String SOIN = "soin";
    private static final String DATE = "date";
    private static final String MONTANT = "montant";

    //STANDARD
    private static final String MSG_JSON_DEBUT = "{\n    \"message\": \"";
    private static final String MSG_JSON_FIN = "\"\n}";

    private static final String ENTER = "\n";
    private static final int INDENTATION = 4;

    //PUBLIC STATIC
    public static String formaterExport(Client client) {

        Map<String, Object> mapClient = new LinkedHashMap<>();
        List<Object> list = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        construireClientExport(client, mapClient, list);

        return gson.toJson(mapClient, LinkedHashMap.class);
    }

    public static FauxClient formaterImport(String stringJson) {

        FauxClient fauxClient = new FauxClient();

        try {
            JSONObject jsonObject = new JSONObject(stringJson);
            boolean choix = verificationChampsClient(jsonObject);
            JSONArray reclamationsJSON = setFauxClient(fauxClient, jsonObject, choix);
            Statistiques.ajoutReclamationsInconnues(reclamationsJSON.length());
        } catch (JSONException e) {
            fauxClient = null;
            ErreurSortie.terminer("json.vide");
        }

        return fauxClient;
    }


    //PRIVATE STATIC
    private static void construireReclamationExport(Client client, List<Object> list) {
        for (Reclamation reclamation : client.getPremiereReclamationMensuelle().getReclamations()) {

            Map<String, Object> mapReclamation = new LinkedHashMap<>();
            mapReclamation.put(SOIN, reclamation.getPoliceSoin().getNumeroSoin());
            mapReclamation.put(DATE, reclamation.getDateReclamation().toString());
            mapReclamation.put(MONTANT, reclamation.getMontantRemboursement().toString());

            list.add(mapReclamation);
        }
    }

    private static void construireClientExport(Client client, Map<String, Object> mapClient, List<Object> list) {
        mapClient.put("dossier", client.getContrat().toString() + client.getNumeroClient());
        mapClient.put(MOIS, client.getDateMois().toStringClient());
        construireReclamationExport(client, list);
        mapClient.put("reclamations", list);
        mapClient.put("total", client.getPremiereReclamationMensuelle().getRemboursementTotal().toString());
    }

    private static JSONArray setFauxClient(FauxClient fauxClient, JSONObject jsonObject, boolean choix) {
        setDossierOuClient(fauxClient, jsonObject, choix);
        fauxClient.setDateMois(String.valueOf(jsonObject.get(MOIS)));
        JSONArray reclamationsJSON = jsonObject.getJSONArray(RECLAMATIONS);
        setFauxReclamations(fauxClient, reclamationsJSON);
        return reclamationsJSON;
    }

    private static void setFauxReclamations(FauxClient fauxClient, JSONArray reclamationsJSON) {
        for (int i = 0; i < reclamationsJSON.length(); i++) {
            JSONObject jsonObject1 = reclamationsJSON.getJSONObject(i);

            verificationChampsReclamations(jsonObject1);
            FauxReclamation fauxReclamation = new FauxReclamation();
            fauxReclamation.setSoin(String.valueOf(reclamationsJSON.getJSONObject(i).get(SOIN)));
            fauxReclamation.setDateReclamation(String.valueOf(reclamationsJSON.getJSONObject(i).get(DATE)));
            fauxReclamation.setMontantReclamation(String.valueOf(reclamationsJSON.getJSONObject(i).get(MONTANT)));
            fauxClient.ajouterReclamation(fauxReclamation);
        }
    }

    private static void setDossierOuClient(FauxClient fauxClient, JSONObject jsonObject, boolean choix) {
        if (choix) {
            fauxClient.setDossier(String.valueOf(jsonObject.get(DOSSIER)));
        } else {
            fauxClient.setNumeroClient(String.valueOf(jsonObject.get(CLIENT)));
            fauxClient.setContrat(String.valueOf(jsonObject.get(CONTRAT)));
        }
    }

    private static boolean verificationChampsClient(JSONObject object) {
        List<String> listStringClient = new ArrayList<>(Arrays.asList(CLIENT, CONTRAT, MOIS, RECLAMATIONS));
        List<String> listStringDossier = new ArrayList<>(Arrays.asList(DOSSIER, MOIS, RECLAMATIONS));
        List<String> listObjet = new ArrayList<>();
        creerListeObjet(object, listObjet, "json.vide");

        listStringDossier.removeAll(listObjet);
        listStringClient.removeAll(listObjet);
        verificationClient(listStringClient, listStringDossier);

        listStringClient = new ArrayList<>(Arrays.asList(CLIENT, CONTRAT, MOIS, RECLAMATIONS));
        listStringDossier = new ArrayList<>(Arrays.asList(DOSSIER, MOIS, RECLAMATIONS));
        contientTousLesChamps(listStringClient, listStringDossier, listObjet);

        return listObjet.contains(DOSSIER);
    }

    private static void contientTousLesChamps(List<String> listStringClient, List<String> listStringDossier, List<String> listObjet) {
        boolean dossier2 = listStringDossier.containsAll(listObjet);
        boolean client2 = listStringClient.containsAll(listObjet);

        if (!dossier2 && !client2) ErreurSortie.terminer("json.champ");
    }

    private static void verificationClient(List<String> listStringClient, List<String> listStringDossier) {
        if (listStringClient.size() > 0 && listStringDossier.size() > 0) {

            listStringClient.addAll(listStringDossier);
            sortieChoixErreurClient(listStringClient);

        }
    }

    public static void sortieChoixErreurClient(List<String> listStringClient) {
        for (String s: listStringClient) {

            switch (s) {
                case CLIENT -> ErreurSortie.terminer("json.client");
                case MOIS -> ErreurSortie.terminer("json.mois");
                case RECLAMATIONS -> ErreurSortie.terminer("json.reclamations");
                case CONTRAT -> ErreurSortie.terminer("json.contrat");
                case DOSSIER -> ErreurSortie.terminer("json.dossier");
            }
        }
    }

    private static void verificationChampsReclamations(JSONObject object) {

        List<String> listStringReclamation = new ArrayList<>(Arrays.asList(SOIN, DATE, MONTANT));
        List<String> listObjet = new ArrayList<>();
        creerListeObjet(object, listObjet, "json.reclamation");

        // Contient tous les fields essentiels
        listStringReclamation.removeAll(listObjet);

        if (listStringReclamation.size() > 0) sortieChoixErreurReclamation(listStringReclamation);
        listStringReclamation = new ArrayList<>(Arrays.asList(SOIN, DATE, MONTANT));

        // Fields extra?
        boolean reclamation2 = listStringReclamation.containsAll(listObjet);
        if (!reclamation2) ErreurSortie.terminer("json.champ");

    }

    private static void creerListeObjet(JSONObject object, List<String> listObjet, String s) {
        try {
            for (Object o : object.names()) listObjet.add(o.toString());
        } catch (Exception e) {
            ErreurSortie.terminer(s);
        }
    }

    public static void sortieChoixErreurReclamation(List<String> listStringReclamation) {
        for (String s: listStringReclamation) {

            switch (s) {
                case SOIN -> ErreurSortie.terminer("json.soin");
                case DATE -> ErreurSortie.terminer("json.date");
                case MONTANT -> ErreurSortie.terminer("json.montant");
            }

        }
    }

}






