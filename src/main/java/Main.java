/**
 * Programme de calcul de reclamation d'assurance
 *
 * @author Etienne Comtois
 * Code permanent: COME17029800
 * Courriel: jb591912@ens.uqam.ca
 *
 * @author David Daoud
 * Code permanent: DAOD80070006
 * Courriel: daoud.david@courrier.uqam.ca
 *
 * @author Loic Dion-Plourde
 * Code permanent: DIOL27099802
 * Courriel: dion-plourde.loic@courrier.uqam.ca
 *
 * @author Jonathan Labelle
 * Code permanent: LABJ28039006
 * Courriel: labelle.jonathan.4@courrier.uqam.ca
 *
 * Cours: inf2050-gr30
 * @version 2021-02-18
 *
 * Copyright (C) 2021 by Etienne Comtois, David Daoud, Loic Dion-Plourde, Jonathan Labelle All right reserved.
 * Released under the terms of the GNU general Public License version 2 or later.
 */

import CheminFichier.CheminFichierFactory;
import Client.Client;
import Client.Clients;
import Client.ClientFactory;
import DecodeurJSON.Database;
import DecodeurJSON.FauxClient.FauxClient;
import DecodeurJSON.JsonDecodeur;
import Erreur.ErreurSortie;
import Fichier.Dossier;
import Fichier.FichierEcrire;
import Fichier.FichierLire;
import Fichier.FichierTemps;
import Reclamation.CalculReclamation;
import Reclamation.ReclamationFactory;
import Statistique.Statistiques;

import static Erreur.ErreurSortie.*;

public class Main {

    public static void main(String[] args) {

        Argument argument = new Argument(args);
        argument.executer();

    }


    //PUBLIC

    public static void calculReclamationPrediction() {
        CheminFichierFactory cheminFichierFactory = creerCheminFichierFactory();
        Clients clients = charger();
        FauxClient fauxClient = creerFauxClient(cheminFichierFactory);
        Client client = creerClientReclamation(clients, fauxClient);
        CalculReclamation.obtenir(clients, client);

        sauvegarderClient(cheminFichierFactory, formaterClient(client));
    }

    public static void calculReclamation() {
        CheminFichierFactory cheminFichierFactory = creerCheminFichierFactory();
        Clients clients = charger();
        FauxClient fauxClient = creerFauxClient(cheminFichierFactory);
        Client client = creerClientReclamation(clients, fauxClient);
        CalculReclamation.obtenir(clients, client);

        clients.add(client);
        Statistiques.compter(client);
        sauvegarder( cheminFichierFactory, formaterClient(client), clients );
    }


    //PRIVATE

    protected static Clients charger() {
        return Database.formaterImport(FichierLire.lire(CheminFichierFactory.getSource().getChemin()));
    }

    protected static CheminFichierFactory creerCheminFichierFactory() {
        Dossier.creer(Dossier.ERREUR);
        Statistiques.commencer();
        CheminFichierFactory cheminFichierFactory = new CheminFichierFactory(Argument.args);
        ErreurSortie.setCheminFichierErreur( cheminFichierFactory.getErreur() ); {
            terminerSiNull(cheminFichierFactory.getIntrant(), "fichier.valide");
            terminerSiNull(FichierLire.lire(cheminFichierFactory.getIntrant().getChemin()), "fichier.existe");
        }
        FichierTemps.lire();
        return cheminFichierFactory;
    }

    private static FauxClient creerFauxClient(CheminFichierFactory cheminFichierFactory) {
        String fichierJsonString = FichierLire.lire(cheminFichierFactory.getIntrant().getChemin());
        FauxClient fauxClient = JsonDecodeur.formaterImport(fichierJsonString); {
            terminerSiNull(fauxClient);
        }
        return fauxClient;
    }

    protected static Client creerClientReclamation(Clients clients, FauxClient fauxClient) {
        Client client = ClientFactory.obtenirClient(fauxClient); {
            terminerSiNull(client);
            ReclamationFactory.ajouterReclamations(clients, client, fauxClient);
        }
        return client;
    }

    private static String formaterClient(Client client) {
        String jsonExport = JsonDecodeur.formaterExport(client); {
            terminerSiNull(jsonExport);
        }
        return jsonExport;
    }

    private static void sauvegarder(CheminFichierFactory cheminFichierFactory, String jsonExport, Clients clients) {
        Dossier.creer(Dossier.DATABASE);
        sauvegarderClient(cheminFichierFactory, jsonExport);
        sauvegarderClients(clients);
        ErreurSortie.sauvegarder();
    }

    private static void sauvegarderClients(Clients clients) {
        FichierEcrire.ecrire(Database.formaterExport(clients), CheminFichierFactory.getSource().getChemin());
    }

    private static void sauvegarderClient(CheminFichierFactory cheminFichierFactory, String jsonExport) {
        String erreur = FichierEcrire.ecrire(jsonExport, cheminFichierFactory.getExtrant().getChemin()); {
            if (erreur != null) terminer("fichier.ecrire");
        }
    }


}