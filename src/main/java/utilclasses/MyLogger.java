/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilclasses;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Andras Sarro <>
 */
public class MyLogger {

    private MyLogger() {
    }
    
    public static final Logger LOGGER = LogManager.getLogger(MyLogger.class);
    
}
