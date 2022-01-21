package Outils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleTest {

    @Test
    @DisplayName("Test la méthode pause pour voir si le programme est sur pause durant 2 secondes")
    void verifiePause() {
        Instant start = Instant.now();
        Console.pause();
        Instant finish = Instant.now();

        //Doit inclure le temps d'exécution du compteur Instant (laisse un 50 ms de jeu)
        assertTrue(2000 <= Duration.between(start, finish).toMillis());
        assertTrue(2050 >= Duration.between(start, finish).toMillis());

    }


}
