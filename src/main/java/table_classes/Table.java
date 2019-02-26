package table_classes;

import information_getters.ReadFromConsole;
import menu.Menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class Table {

    private static String currentTableName = "groceryprices";

    public static String getCurrentTableName() {
        return currentTableName;
    }

    public static void setCurrentTableName() {

        int answer;

        System.out.println("\nTables available: "
                + "\n1\t- \"groceryprices\""
                + "\n0\t- don't change");

        answer = Integer.parseInt(ReadFromConsole.readFromConsole());

        switch (answer) {
            case 1:
                Table.currentTableName = "groceryprices";
                System.out.println("\nThe current table is: " + Table.getCurrentTableName());
                Menu.askWhatToDoNext();

            case 0:
                System.out.println("\nThe current table remains: " + Table.getCurrentTableName());
                Menu.askWhatToDoNext();

            default:
                System.out.println("The answer should be one of the key numbers.");
                Table.setCurrentTableName();

        }
    }

    public static Table createTable() {

        Table table = null;

        String tableName = Table.getCurrentTableName();

        if (tableName.equals("groceryprices")) {
            table = new GroceryPrices();
        } else {
            System.out.println("It's not a name of a table.");
        }
        return table;
    }

    public abstract void setEntriesFromResultSet(ResultSet resultSet);

    public abstract void composeRowContentsList();

    public abstract void showTableOneRow();

    public abstract void askForValues();

    public abstract String getValues();

    public abstract PreparedStatement prepareAddRowStatement(PreparedStatement preparedStatement);
}
