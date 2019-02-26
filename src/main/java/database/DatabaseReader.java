package database;

import menu.Menu;
import table_classes.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Delivers SQL-queries into the database and gets the results.
 *
 * STATIC
 */
public class DatabaseReader {

    public static void readTheTable(String query) {

        PreparedStatement preparedStatement = null;
        List<Table> table = new ArrayList<Table>();

        try {
            preparedStatement = DatabaseConnection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Table tableRow = Table.createTable();

                tableRow.setEntriesFromResultSet(resultSet);
                tableRow.composeRowContentsList();
                table.add(tableRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        for (Table row : table) {
            row.showTableOneRow();
        }

        Menu.askWhatToDoNext();
    }
}
