
package Outils;

import java.util.Scanner;

public class Console {


    // PAUSE

    /**
     * Prend une pause de durée déterminée
     * @param milliSecondes durée de la pause
     */
    public static void pause(int milliSecondes) {
        try {
            Thread.sleep(milliSecondes);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Prend une pause de 2 secondes
     */
    public static void pause() {
        pause(2000);
    }

}
