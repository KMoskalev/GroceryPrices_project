package information_getters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Reads the information from the console.
 * Returns a String containing that information.
 *
 * STATIC
 */
public class ReadFromConsole {

    public static String readFromConsole() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String consoleInput = " ";
        try {
            consoleInput = consoleReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return consoleInput;
    }
}
