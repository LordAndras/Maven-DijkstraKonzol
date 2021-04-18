/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilclasses;

import dijkstrakonzol.GrafModel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Andras Sarro <>
 */
public class GrafSaver {

    private static final String DEFAULT_SAVE_PATH = "c:/grafjaro/graf.dat";

    private GrafSaver() {
    }

    public static void saveGrafWithFilePath(String filePath, GrafModel gm) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(gm);
            MyLogger.LOGGER.info("A mentés sikeres!");
        } catch (IOException ex) {
            MyLogger.LOGGER.info("File hiba!" + ex);
        }
    }

    public static void saveGraf(GrafModel gm) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DEFAULT_SAVE_PATH))) {
            oos.writeObject(gm);
            MyLogger.LOGGER.info("A mentés sikeres!");
        } catch (IOException ex) {
            MyLogger.LOGGER.info("File hiba!" + ex);
        }

    }

}
