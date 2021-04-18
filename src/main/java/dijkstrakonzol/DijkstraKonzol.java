/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstrakonzol;

import java.util.List;
import java.util.Scanner;
import utilclasses.GrafLoader;
import utilclasses.GrafSaver;
import utilclasses.MyLogger;

/**
 *
 * @author Andras Sarro <>
 */
public class DijkstraKonzol {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static GrafModel gm;
    private static List<Character> nodeLetters;
    private static boolean rightData = false;
    private static boolean wasLoaded = false;

    public static void main(String[] args) {
        MyLogger.LOGGER.info("A program a gráf csomópontjaira az angol ABC nagybetűivel hivatkozik, <A, B, C, ...>");
        MyLogger.LOGGER.info("A csomópontok közötti távolságot / súlyozást EGÉSZ számokban méri!");
        MyLogger.LOGGER.info("A csomópontok közötti kapcsolatot manuálisan kell megadni!");
        MyLogger.LOGGER.info("");

        newGraf();

        if (!wasLoaded) {
            setNeighbourNodes();
        }

        MyLogger.LOGGER.info("Ellenőrzöd az adatokat? I / N");
        String willCheck = SCANNER.nextLine();
        if (willCheck.equalsIgnoreCase("I")) {
            gm.grafChecker();
        }

        if (!wasLoaded) {
            MyLogger.LOGGER.info("Elmented a gráfot? I / N");
            String willSave = SCANNER.nextLine();
            if (willSave.equalsIgnoreCase("I")) {
                String filePath = "";
                MyLogger.LOGGER.info("Add meg az elérési utat, a fájl nevével bezárólag! <pl: c:/proba/file.dat \n Ha ÜRESEN hagyod,"
                        + "a c:/grafjaro/graf.dat\" lesz a mentés helye.");
                filePath = SCANNER.nextLine();
                if (filePath.isEmpty()) {
                    GrafSaver.saveGraf(gm);
                } else {
                    GrafSaver.saveGrafWithFilePath(filePath.toLowerCase(), gm);
                }
            }
        }

        MyLogger.LOGGER.info("Betölthetem az adatokat? I / N");
        String readyToRun = SCANNER.nextLine();
        if (readyToRun.equalsIgnoreCase("I")) {
            start();
        } else {
            while (!rightData) {
                newGraf();
                setNeighbourNodes();
                rightData = checkedData();
            }
            start();

        }

    }

    private static void setNeighbourNodes() {
        for (Character ch : nodeLetters) {
            MyLogger.LOGGER.info("Hány szomszédja van '" + ch + "'-nak, amit még NEM ADTÁL MEG?");
            int numberOfNeighbours = SCANNER.nextInt();
            SCANNER.nextLine();
            for (int i = 0; i < numberOfNeighbours; i++) {
                MyLogger.LOGGER.info("Kérem '" + ch + "' " + (i + 1) + ". NEM MEGADOTT szomszédját, és a két csomópont távolsását! <pl: B 2>");
                String s = SCANNER.nextLine().toUpperCase();
                char x = s.charAt(0);
                int y = Integer.parseInt("" + s.substring(2));

                gm.setGraphEdge(ch, x, y);
            }
        }
    }

    private static void newGraf() {
        MyLogger.LOGGER.info("Új gráfot írsz, vagy egy meglévőt töltesz be? I - új gráf / N - betöltés");
        String willLoad = SCANNER.nextLine();

        if (willLoad.equalsIgnoreCase("N")) {
            gm = new GrafModel();
            MyLogger.LOGGER.info("Add meg a betöltendő gráf elérési útját! <pl: c:/konyvtar/graf.dat>");
            String loadFromHere = SCANNER.nextLine();
            gm = GrafLoader.loadGrafFromSource(loadFromHere.toLowerCase());
            wasLoaded = true;
        } else {
            MyLogger.LOGGER.info("Hány csomópontja van a gráfnak? Egész számot írj! \nCsomópontok száma:");
            int csucsok = SCANNER.nextInt();
            gm = new GrafModel(csucsok);
            nodeLetters = gm.getNemLatogatott();
        }
    }

    private static void start() {
        MyLogger.LOGGER.info("Kérem a kiindulási csomópont BETŰJÉT! <pl: C>");
        String startCel = SCANNER.nextLine();
        gm.setStart((char) startCel.toUpperCase().charAt(0));

        GrafJaro jaro = new GrafJaro(gm);
        jaro.jardBe();
    }

    private static boolean checkedData() {
        MyLogger.LOGGER.info("Jó lett? I / N");
        String valasz = SCANNER.nextLine();
        return valasz.equalsIgnoreCase("I");

    }
}
