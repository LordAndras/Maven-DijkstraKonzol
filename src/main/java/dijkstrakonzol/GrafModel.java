/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstrakonzol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utilclasses.MyLogger;

public class GrafModel implements Serializable {

    private static final long serialVersionUID = 1234;
    private static final int KARAKTER_A = 65;
    private int csucsokSzama;
    private List<Character> nemLatogatott;
    private List<Map<Character, Integer>> csucsMapList;
    private char start = 'A';

    public GrafModel() {
    }

    public GrafModel(int csucsokSzama) {
        this.csucsokSzama = csucsokSzama;
        this.nemLatogatott = new ArrayList<>();
        this.csucsMapList = new ArrayList<>();

        for (int i = KARAKTER_A; i < KARAKTER_A + csucsokSzama; i++) {
            nemLatogatott.add((char) i);
        }

        mapInit();

    }

    private void mapInit() {
        for (int j = 0; j < csucsokSzama; j++) {
            csucsMapList.add(new HashMap<>());
            csucsMapList.get(j).put((char) (KARAKTER_A + j), 0);
        }

    }

    public void setGraphEdge(char origo, char b, int i) {

        Map<Character, Integer> aktMap;
        Map<Character, Integer> masikCsucsMap;

        for (Character ch : nemLatogatott) {

            if (ch == origo) {
                aktMap = csucsMapList.get((int) ch - KARAKTER_A);
                aktMap.computeIfAbsent(b, k -> i);

            }

            masikCsucsMap = csucsMapList.get((int) b - KARAKTER_A);
            masikCsucsMap.computeIfAbsent(origo, k -> i);

        }

    }

    public void grafChecker() {
        for (Map<Character, Integer> map : csucsMapList) {
            for (Object entry : map.entrySet()) {
                Map.Entry<Character, Integer> beiras = (Map.Entry<Character, Integer>) entry;
                MyLogger.LOGGER.info(beiras.getKey() + " : " + beiras.getValue());
            }
            MyLogger.LOGGER.info("---------");
        }
    }

    public List<Character> getNemLatogatott() {
        return nemLatogatott;
    }

    public List<Map<Character, Integer>> getCsucsMapList() {
        return csucsMapList;
    }

    public char getStart() {
        return start;
    }

    public void setStart(char start) {
        this.start = start;
    }

    public int getKARAKTER_A() {
        return KARAKTER_A;
    }

    public int getCsucsokSzama() {
        return csucsokSzama;
    }

}
