/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstrakonzol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utilclasses.MyLogger;
/**
 *
 * @author Andras Sarro <>
 */
public class GrafJaro {

    private List<Character> latogatottCsucsok;
    private GrafModel gm;
    private char start;
    private char aktualisCsucs;
    private int aktualisNode = 0;
    private Map<Character, Integer> aktualisMap;
    private long[] aktualisTavok;
    private long[] teljesTavStarttol;
    private int[] elozoNode;

    public GrafJaro() {
    }

    public GrafJaro(GrafModel gm) {
        this.gm = gm;
        this.start = gm.getStart();
        aktualisCsucs = start;

        latogatottCsucsok = new ArrayList<>();
        aktualisMap = new HashMap<>();
        aktualisTavok = new long[gm.getCsucsokSzama()];
        elozoNode = new int[gm.getCsucsokSzama()];
        teljesTavStarttol = new long[gm.getCsucsokSzama()];

        for (long tavolsag : teljesTavStarttol) {
            if (tavolsag == 0) {
                tavolsag = Long.MAX_VALUE;
            }
        }

    }

    void jardBe() {

        while (!gm.getNemLatogatott().isEmpty()) {
            csucsMapBetolt();
            mapBejar();
        }

        for (int i = 0; i < teljesTavStarttol.length; i++) {
            MyLogger.LOGGER.info("A(z) " + (char) (gm.getKARAKTER_A() + i) + " csomópont távolsága " + start + ". csomóponttól: "
                    + teljesTavStarttol[i] + " egység. Megelőző csomópont: " + (char) (gm.getKARAKTER_A() + elozoNode[i]));
        }

    }

    private char minKeres(long[] tomb) {
        int minIndex = -1;
        long aktualisSzam = Long.MAX_VALUE;

        for (int i = 0; i < tomb.length; i++) {
            if (tomb[i] < aktualisSzam && tomb[i] != 0 && gm.getNemLatogatott().contains((char) (gm.getKARAKTER_A() + i))) {
                aktualisSzam = tomb[i];
                minIndex = i;
            }
        }
        return (char) (gm.getKARAKTER_A() + minIndex);

    }

    private void csucsMapBetolt() {
        aktualisNode = (int) aktualisCsucs - gm.getKARAKTER_A();
        aktualisMap = gm.getCsucsMapList().get(aktualisNode);

        for (Character ch : gm.getNemLatogatott()) {
            if (aktualisMap.get(ch) != null) {
                aktualisTavok[(int) (ch - gm.getKARAKTER_A())] = aktualisMap.get(ch);
            } else {
                aktualisTavok[(int) (ch - gm.getKARAKTER_A())] = Long.MAX_VALUE;
            }
        }
    }

    private void mapBejar() {
        for (int i = 0; i < gm.getCsucsokSzama(); i++) {
            if (aktualisCsucs == start) {
                teljesTavStarttol[i] = aktualisTavok[i];
                if (aktualisTavok[i] != Long.MAX_VALUE) {
                    elozoNode[i] = aktualisNode;
                }
            } else {
                long tavStarttol = teljesTavStarttol[aktualisNode];
                if (aktualisTavok[i] != Long.MAX_VALUE && aktualisTavok[i] + tavStarttol < teljesTavStarttol[i]) {
                    teljesTavStarttol[i] = aktualisTavok[i] + tavStarttol;
                    elozoNode[i] = aktualisNode;
                }
            }
        }
        gm.getNemLatogatott().remove((Character) aktualisCsucs);
        latogatottCsucsok.add((Character) aktualisCsucs);
        aktualisCsucs = minKeres(teljesTavStarttol);

    }
}
