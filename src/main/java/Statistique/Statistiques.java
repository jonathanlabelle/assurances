package Statistique;

import CheminFichier.CheminFichierFactory;
import Client.Client;
import Client.Clients;
import Dollar.DollarFactory;
import Erreur.ErreurSortie;
import Fichier.FichierTemps;
import Reclamation.Reclamation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe qui permet le suivi des statistiques.
 *
 * @author David Daoud
 * Code permanent: DAOD80070006
 * Courriel: daoud.david@courrier.uqam.ca
 * @author Loic Dion-Plourde
 * Code permanent: DIOL27099802
 * Courriel: dion-plourde.loic@courrier.uqam.ca
 * <p>
 * Copyright (C) 2021 by David Daoud, Loic Dion-Plourde, All right reserved.
 * Released under the terms of the GNU general Public License version 2 or later.
 */

public class Statistiques {

    //VARIABLES

    protected static int reclamationsValides;
    protected static int reclamationsInvalides;

    protected static int reclamationsInconnues;

    protected static StatistiqueSoin statistiqueSoin000 = new StatistiqueSoin(000);
    protected static StatistiqueSoin statistiqueSoin100 = new StatistiqueSoin(100);
    protected static StatistiqueSoin statistiqueSoin150 = new StatistiqueSoin(150);
    protected static StatistiqueSoin statistiqueSoin175 = new StatistiqueSoin(175);
    protected static StatistiqueSoin statistiqueSoin200 = new StatistiqueSoin(200);
    protected static StatistiqueSoin statistiqueSoin300 = new StatistiqueSoin(300);
    protected static StatistiqueSoin statistiqueSoin400 = new StatistiqueSoin(400);
    protected static StatistiqueSoin statistiqueSoin500 = new StatistiqueSoin(500);
    protected static StatistiqueSoin statistiqueSoin600 = new StatistiqueSoin(600);
    protected static StatistiqueSoin statistiqueSoin700 = new StatistiqueSoin(700);


    //GET

    public static int getReclamationsValides() { return reclamationsValides; }
    public static int getReclamationsInvalides() { return reclamationsInvalides; }
    public static int getReclamationsInconnues() { return reclamationsInconnues; }

    public static StatistiqueSoin getStatistiqueSoin000() { return statistiqueSoin000; }
    public static StatistiqueSoin getStatistiqueSoin100() { return statistiqueSoin100; }
    public static StatistiqueSoin getStatistiqueSoin150() { return statistiqueSoin150; }
    public static StatistiqueSoin getStatistiqueSoin175() { return statistiqueSoin175; }
    public static StatistiqueSoin getStatistiqueSoin200() { return statistiqueSoin200; }
    public static StatistiqueSoin getStatistiqueSoin300() { return statistiqueSoin300; }
    public static StatistiqueSoin getStatistiqueSoin400() { return statistiqueSoin400; }
    public static StatistiqueSoin getStatistiqueSoin500() { return statistiqueSoin500; }
    public static StatistiqueSoin getStatistiqueSoin600() { return statistiqueSoin600; }
    public static StatistiqueSoin getStatistiqueSoin700() { return statistiqueSoin700; }


    //SET

    public static void setReclamationsValides(int reclamationsValides) { Statistiques.reclamationsValides = reclamationsValides; }
    public static void setReclamationsInvalides(int reclamationsInvalides) { Statistiques.reclamationsInvalides = reclamationsInvalides; }
    public static void setReclamationsInconnues(int reclamationsInconnues) { Statistiques.reclamationsInconnues = reclamationsInconnues; }
    public static void setStatistiqueSoin000(StatistiqueSoin statistiqueSoin000) { Statistiques.statistiqueSoin000 = statistiqueSoin000; }
    public static void setStatistiqueSoin100(StatistiqueSoin statistiqueSoin100) { Statistiques.statistiqueSoin100 = statistiqueSoin100; }
    public static void setStatistiqueSoin150(StatistiqueSoin statistiqueSoin150) { Statistiques.statistiqueSoin150 = statistiqueSoin150; }
    public static void setStatistiqueSoin175(StatistiqueSoin statistiqueSoin175) { Statistiques.statistiqueSoin175 = statistiqueSoin175; }
    public static void setStatistiqueSoin200(StatistiqueSoin statistiqueSoin200) { Statistiques.statistiqueSoin200 = statistiqueSoin200; }
    public static void setStatistiqueSoin300(StatistiqueSoin statistiqueSoin300) { Statistiques.statistiqueSoin300 = statistiqueSoin300; }
    public static void setStatistiqueSoin400(StatistiqueSoin statistiqueSoin400) { Statistiques.statistiqueSoin400 = statistiqueSoin400; }
    public static void setStatistiqueSoin500(StatistiqueSoin statistiqueSoin500) { Statistiques.statistiqueSoin500 = statistiqueSoin500; }
    public static void setStatistiqueSoin600(StatistiqueSoin statistiqueSoin600) { Statistiques.statistiqueSoin600 = statistiqueSoin600; }
    public static void setStatistiqueSoin700(StatistiqueSoin statistiqueSoin700) { Statistiques.statistiqueSoin700 = statistiqueSoin700; }


    //METHODES

    public static void commencer() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(CheminFichierFactory.getStatistique().getChemin())) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;


            reclamationsValides = (Integer.parseInt(jsonObject.get("reclamationsValides").toString()));
            reclamationsInvalides = (Integer.parseInt(jsonObject.get("reclamationsInvalides").toString()));


            statistiqueSoin000.setNombreSoin(Integer.parseInt(jsonObject.get("soin000").toString()));
            statistiqueSoin000.setMontantMaximal(DollarFactory.getDollar(jsonObject.get("Max soin000").toString()));
            statistiqueSoin000.setMontantMoyen(DollarFactory.getDollar(jsonObject.get("Moyenne soin000").toString()));

            statistiqueSoin100.setNombreSoin(Integer.parseInt(jsonObject.get("soin100").toString()));
            statistiqueSoin100.setMontantMaximal(DollarFactory.getDollar(jsonObject.get("Max soin100").toString()));
            statistiqueSoin100.setMontantMoyen(DollarFactory.getDollar(jsonObject.get("Moyenne soin100").toString()));

            statistiqueSoin150.setNombreSoin(Integer.parseInt(jsonObject.get("soin150").toString()));
            statistiqueSoin150.setMontantMaximal(DollarFactory.getDollar(jsonObject.get("Max soin150").toString()));
            statistiqueSoin150.setMontantMoyen(DollarFactory.getDollar(jsonObject.get("Moyenne soin150").toString()));

            statistiqueSoin175.setNombreSoin(Integer.parseInt(jsonObject.get("soin175").toString()));
            statistiqueSoin175.setMontantMaximal(DollarFactory.getDollar(jsonObject.get("Max soin175").toString()));
            statistiqueSoin175.setMontantMoyen(DollarFactory.getDollar(jsonObject.get("Moyenne soin175").toString()));

            statistiqueSoin200.setNombreSoin(Integer.parseInt(jsonObject.get("soin200").toString()));
            statistiqueSoin200.setMontantMaximal(DollarFactory.getDollar(jsonObject.get("Max soin200").toString()));
            statistiqueSoin200.setMontantMoyen(DollarFactory.getDollar(jsonObject.get("Moyenne soin200").toString()));

            statistiqueSoin300.setNombreSoin(Integer.parseInt(jsonObject.get("soin300").toString()));
            statistiqueSoin300.setMontantMaximal(DollarFactory.getDollar(jsonObject.get("Max soin300").toString()));
            statistiqueSoin300.setMontantMoyen(DollarFactory.getDollar(jsonObject.get("Moyenne soin300").toString()));

            statistiqueSoin400.setNombreSoin(Integer.parseInt(jsonObject.get("soin400").toString()));
            statistiqueSoin400.setMontantMaximal(DollarFactory.getDollar(jsonObject.get("Max soin400").toString()));
            statistiqueSoin400.setMontantMoyen(DollarFactory.getDollar(jsonObject.get("Moyenne soin400").toString()));

            statistiqueSoin500.setNombreSoin(Integer.parseInt(jsonObject.get("soin500").toString()));
            statistiqueSoin500.setMontantMaximal(DollarFactory.getDollar(jsonObject.get("Max soin500").toString()));
            statistiqueSoin500.setMontantMoyen(DollarFactory.getDollar(jsonObject.get("Moyenne soin500").toString()));

            statistiqueSoin600.setNombreSoin(Integer.parseInt(jsonObject.get("soin600").toString()));
            statistiqueSoin600.setMontantMaximal(DollarFactory.getDollar(jsonObject.get("Max soin600").toString()));
            statistiqueSoin600.setMontantMoyen(DollarFactory.getDollar(jsonObject.get("Moyenne soin600").toString()));

            statistiqueSoin700.setNombreSoin(Integer.parseInt(jsonObject.get("soin700").toString()));
            statistiqueSoin700.setMontantMaximal(DollarFactory.getDollar(jsonObject.get("Max soin700").toString()));
            statistiqueSoin700.setMontantMoyen(DollarFactory.getDollar(jsonObject.get("Moyenne soin700").toString()));


        } catch (FileNotFoundException e) {
            ErreurSortie.erreur("stats.existe");
        } catch (IOException e) {
            ErreurSortie.erreur("fichier.lire");
        } catch (ParseException e) {
            ErreurSortie.terminer("stats.modif");
        }

    }

    public static void terminer() {

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("reclamationsValides", String.valueOf(reclamationsValides));
        map.put("reclamationsInvalides", String.valueOf(reclamationsInvalides));

        map.put("soin000", statistiqueSoin000.getNombreSoin());
        map.put("Max soin000", statistiqueSoin000.getMontantMaximal().toString());
        map.put("Moyenne soin000", statistiqueSoin000.getMontantMoyen().toString());

        map.put("soin100", statistiqueSoin100.getNombreSoin());
        map.put("Max soin100", statistiqueSoin100.getMontantMaximal().toString());
        map.put("Moyenne soin100", statistiqueSoin100.getMontantMoyen().toString());

        map.put("soin150", statistiqueSoin150.getNombreSoin());
        map.put("Max soin150", statistiqueSoin150.getMontantMaximal().toString());
        map.put("Moyenne soin150", statistiqueSoin150.getMontantMoyen().toString());

        map.put("soin175", statistiqueSoin175.getNombreSoin());
        map.put("Max soin175", statistiqueSoin175.getMontantMaximal().toString());
        map.put("Moyenne soin175", statistiqueSoin175.getMontantMoyen().toString());

        map.put("soin200", statistiqueSoin200.getNombreSoin());
        map.put("Max soin200", statistiqueSoin200.getMontantMaximal().toString());
        map.put("Moyenne soin200", statistiqueSoin200.getMontantMoyen().toString());

        map.put("soin300", statistiqueSoin300.getNombreSoin());
        map.put("Max soin300", statistiqueSoin300.getMontantMaximal().toString());
        map.put("Moyenne soin300", statistiqueSoin300.getMontantMoyen().toString());

        map.put("soin400", statistiqueSoin400.getNombreSoin());
        map.put("Max soin400", statistiqueSoin400.getMontantMaximal().toString());
        map.put("Moyenne soin400", statistiqueSoin400.getMontantMoyen().toString());

        map.put("soin500", statistiqueSoin500.getNombreSoin());
        map.put("Max soin500", statistiqueSoin500.getMontantMaximal().toString());
        map.put("Moyenne soin500", statistiqueSoin500.getMontantMoyen().toString());

        map.put("soin600", statistiqueSoin600.getNombreSoin());
        map.put("Max soin600", statistiqueSoin600.getMontantMaximal().toString());
        map.put("Moyenne soin600", statistiqueSoin600.getMontantMoyen().toString());

        map.put("soin700", statistiqueSoin700.getNombreSoin());
        map.put("Max soin700", statistiqueSoin700.getMontantMaximal().toString());
        map.put("Moyenne soin700", statistiqueSoin700.getMontantMoyen().toString());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter(CheminFichierFactory.getStatistique().getChemin())) {
            file.write(gson.toJson(map, LinkedHashMap.class));
        } catch (IOException e) {
            ErreurSortie.erreur("fichier.ecrire");
        }

    }

    public static void ajoutReclamationsValides(int i) {
        reclamationsValides = (i + reclamationsValides);
    }

    public static void ajoutReclamationsInvalides(int i) {
        reclamationsInvalides = (i + reclamationsInvalides);
    }

    public static void ajoutReclamationsInconnues(int i) {
        reclamationsInconnues = i;
    }

    public static void recommencerStats() {

        reclamationsValides = 0;
        reclamationsInvalides = 0;

        statistiqueSoin000 = new StatistiqueSoin(000);
        statistiqueSoin100 = new StatistiqueSoin(100);
        statistiqueSoin150 = new StatistiqueSoin(150);
        statistiqueSoin175 = new StatistiqueSoin(175);
        statistiqueSoin200 = new StatistiqueSoin(200);
        statistiqueSoin300 = new StatistiqueSoin(300);
        statistiqueSoin400 = new StatistiqueSoin(400);
        statistiqueSoin500 = new StatistiqueSoin(500);
        statistiqueSoin600 = new StatistiqueSoin(600);
        statistiqueSoin700 = new StatistiqueSoin(700);

    }
    
    public static String afficher() {
        StringBuilder stringBuilder = new StringBuilder();
        construireEntete(stringBuilder);
        construireSoins(stringBuilder);

        return stringBuilder.toString();
    }

    private static boolean construireSoins(StringBuilder stringBuilder) {
        construireSoin(stringBuilder, statistiqueSoin000);
        construireSoin(stringBuilder, statistiqueSoin100);
        construireSoin(stringBuilder, statistiqueSoin150);
        construireSoin(stringBuilder, statistiqueSoin175);
        construireSoin(stringBuilder, statistiqueSoin200);
        construireSoin(stringBuilder, statistiqueSoin300);
        construireSoin(stringBuilder, statistiqueSoin400);
        construireSoin(stringBuilder, statistiqueSoin500);
        construireSoin(stringBuilder, statistiqueSoin600);
        construireSoin(stringBuilder, statistiqueSoin700);
        return true;
    }

    private static boolean construireEntete(StringBuilder stringBuilder) {
        stringBuilder.append("STATISTIQUES\n");
        stringBuilder.append("\tNombre de réclamations traitées : ");
        stringBuilder.append(reclamationsValides);
        stringBuilder.append("\n");

        stringBuilder.append("\tNombre de demandes rejetées : ");
        stringBuilder.append(reclamationsInvalides);
        stringBuilder.append("\n");

        stringBuilder.append("\tStatistiques des soins :\n");

        return true;
    }

    private static boolean construireSoin(StringBuilder stringBuilder, StatistiqueSoin statistiqueSoin) {
        stringBuilder.append("\t\tSoin numero ");
        stringBuilder.append(String.format("%03d\n", statistiqueSoin.getNumeroSoin()));

        stringBuilder.append( String.format("\t\t\tNombre  : %s\n", statistiqueSoin.getNombreSoin()) );
        stringBuilder.append( String.format("\t\t\tMoyenne : %s\n", statistiqueSoin.getMontantMoyen().toString()) );
        stringBuilder.append( String.format("\t\t\tMaximum : %s\n", statistiqueSoin.getMontantMaximal().toString()) );

        return true;
    }

    public static void setReclamationsInconnues(boolean estValide) {
        if (estValide) ajoutReclamationsValides(reclamationsInconnues);
        else ajoutReclamationsInvalides(1);
    }


    public static void compter(Client client) {

        List<Reclamation> reclamations = client.getPremiereReclamationMensuelle().getReclamations();

        for (Reclamation reclamation : reclamations) {
            int nbSoin = reclamation.getPoliceSoin().getNumeroSoin();

            switch (nbSoin) {
                case 0 -> statistiqueSoin000.ajouterSoin(reclamation.getMontantReclamation());
                case 100 -> statistiqueSoin100.ajouterSoin(reclamation.getMontantReclamation());
                case 150 -> statistiqueSoin150.ajouterSoin(reclamation.getMontantReclamation());
                case 175 -> statistiqueSoin175.ajouterSoin(reclamation.getMontantReclamation());
                case 200 -> statistiqueSoin200.ajouterSoin(reclamation.getMontantReclamation());
                case 400 -> statistiqueSoin400.ajouterSoin(reclamation.getMontantReclamation());
                case 500 -> statistiqueSoin500.ajouterSoin(reclamation.getMontantReclamation());
                case 600 -> statistiqueSoin600.ajouterSoin(reclamation.getMontantReclamation());
                case 700 -> statistiqueSoin700.ajouterSoin(reclamation.getMontantReclamation());
                default -> { if (estEntre(nbSoin, 300, 399))
                    statistiqueSoin300.ajouterSoin(reclamation.getMontantReclamation());
                }
            }

        }

    }

    public static boolean estEntre(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }


}
