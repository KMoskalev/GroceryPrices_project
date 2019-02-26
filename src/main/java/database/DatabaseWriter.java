package database;

import information_getters.ReadFromConsole;
import menu.Menu;
import queries.Queries;
import table_classes.Table;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Writes the data into the database.
 *
 * STATIC
 */
public class DatabaseWriter {

    public static void askForValuesToAdd() {

        Table tableRow = Table.createTable();

        tableRow.askForValues();

        String newRowQuery = Queries.insertValues(tableRow);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DatabaseConnection.getConnection().prepareStatement(newRowQuery);
            preparedStatement = tableRow.prepareAddRowStatement(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nThe information is saved into the database.");

        DatabaseWriter.askForMoreProducts();
    }

    private static void askForMoreProducts() {

        String answer;

        System.out.println("\nMore products? Y/N");

        answer = ReadFromConsole.readFromConsole();
        if (answer.equals("Y") || answer.equals("y")) {
            DatabaseWriter.askForValuesToAdd();
        } else if (answer.equals("N") || answer.equals("n")) {
            Menu.askWhatToDoNext();
        } else {
            System.out.println("\nThe answer should be \"Y\" or \"N\".");
            DatabaseWriter.askForMoreProducts();
        }
    }
}
