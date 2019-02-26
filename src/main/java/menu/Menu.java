package menu;

import database.*;
import information_getters.ReadFromConsole;
import queries.Queries;
import table_classes.GroceryPrices;
import table_classes.Table;

/**
 * Offers the user a choice what to do.
 *
 * STATIC
 */
public class Menu {

    public static void askWhatToDo() {

        System.out.println("\nWhat would you like to do?");
        Menu.ask();
    }

    public static void askWhatToDoNext() {

        System.out.println("\nWhat would you like to do next?");
        Menu.ask();
    }

    private static void ask() {

        System.out.println("(current table: " + Table.getCurrentTableName() + ")\n"
                + "\n1\t- choose a table to work with"
                + "\n2\t- add information into the current table"
                + "\n3\t- read all the information from the current table"
                + "\n4\t- read specific information from the current table"
                + "\n0\t- exit the program");

        int answer;
        answer = Integer.parseInt(ReadFromConsole.readFromConsole());

        switch (answer) {
            case 1:
                Table.setCurrentTableName();

            case 2:
                DatabaseWriter.askForValuesToAdd();

            case 3:
                DatabaseReader.readTheTable(Queries.selectAll());

            case 4:
                Menu.askForSpecificInformation();

            case 5:
                // "\n5\t- see the analyzed information"
                // There should be several options
                System.out.println("\nThis function is not implemented yet.");
                Menu.askWhatToDo();

            case 0:
                DatabaseConnection.closeConnection();
                System.exit(1);

            default:
                System.out.println("The answer should be one of the key numbers.");
                Menu.askWhatToDo();
        }
    }

    private static void askForSpecificInformation() {

        System.out.println("\nSpecific information about:"
                + "\n1\t- some product"
                + "\n2\t- some shop"
                + "\n3\t- some product from some shop"
                + "\n0\t- return to the main menu");

        int answer;
        answer = Integer.parseInt(ReadFromConsole.readFromConsole());

        switch (answer) {
            case 1:
                GroceryPrices.productsAvailable();
                System.out.println("\n\nChoose a product:");
                String productName = ReadFromConsole.readFromConsole();
                DatabaseReader.readTheTable(Queries.selectSpecificProduct(productName));
                Menu.askWhatToDoNext();

            case 2:
                GroceryPrices.shopsAvailable();
                System.out.println("\n\n Choose a shop:");
                String shopName = ReadFromConsole.readFromConsole();
                DatabaseReader.readTheTable(Queries.selectSpecificShop(shopName));
                Menu.askWhatToDoNext();

            case 3:
                GroceryPrices.productsAvailable();
                System.out.println("\n\nChoose a product:");
                String product = ReadFromConsole.readFromConsole();
                GroceryPrices.shopsAvailable();
                System.out.println("\n\nChoose a shop:");
                String shop = ReadFromConsole.readFromConsole();
                DatabaseReader.readTheTable(Queries.selectSpecificProductFromShop(product, shop));
                Menu.askWhatToDoNext();

            case 0:
                Menu.askWhatToDo();

            default:
                System.out.println("The answer should be one of the key numbers.");
                Menu.askForSpecificInformation();
        }
    }
}
