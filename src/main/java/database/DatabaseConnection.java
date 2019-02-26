package database;

import information_getters.ReadFromConsole;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database manager has methods for connecting to the database and for closing the connection.
 *
 * STATIC
 */
public class DatabaseConnection {

    private static Connection connection = null;

    private static String databaseURL = "jdbc:postgresql:Grocery_Prices_Database";
    private static String userName = "postgres";
    private static String password = "my100500pass";

    public static void newConnection() {

        String answer;
        System.out.println("\nWould you like to use the default connection to the database? Y/N");
        answer = ReadFromConsole.readFromConsole();
        if (answer.equals("Y") || answer.equals("y")) {
            DatabaseConnection.connectToDatabase();
        } else if (answer.equals("N") || answer.equals("n")) {

            System.out.print("\nDatabase URL (it looks like jdbc:postgresql:Database_name): ");
            databaseURL = ReadFromConsole.readFromConsole();

            System.out.print("User name: ");
            userName = ReadFromConsole.readFromConsole();

            System.out.print("Password: ");
            password = ReadFromConsole.readFromConsole();

            DatabaseConnection.connectToDatabase();

        } else {
            System.out.println("\nThe answer should be \"Y\" or \"N\".");
            DatabaseConnection.newConnection();
        }
    }

    private static void connectToDatabase() {

        try {
            connection = DriverManager.getConnection(databaseURL, userName, password);

            System.out.println("\nConnected to database.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {

        try {
            DatabaseConnection.getConnection().close();

            System.out.println("\nConnection to database closed.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
