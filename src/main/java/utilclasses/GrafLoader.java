/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilclasses;

import dijkstrakonzol.GrafModel;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author Andras Sarro <>
 */
public class GrafLoader {

    private GrafLoader() {
    }

    public static GrafModel loadGrafFromSource(String filePath) {
        GrafModel loadedGrafModel = new GrafModel();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            loadedGrafModel = (GrafModel) ois.readObject();
            MyLogger.LOGGER.info("A betöltés sikerült!");
        } catch (IOException ex) {
            MyLogger.LOGGER.info("File hiba! " + ex);
            MyLogger.LOGGER.info("A program leáll.");
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            MyLogger.LOGGER.info("A gráf nem gráf... " + ex);
            MyLogger.LOGGER.info("A program leáll.");
            System.exit(0);
        }

        return loadedGrafModel;
    }
}
