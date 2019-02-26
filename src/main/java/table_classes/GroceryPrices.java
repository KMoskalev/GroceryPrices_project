package table_classes;

import database.DatabaseConnection;
import information_getters.ReadFromConsole;
import queries.Queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GroceryPrices extends Table {

    private String date;
    private String time;
    private String productName;
    private double productPrice;
    private String shopName;

    private void askForProductName() {

        System.out.println("\nProduct name: ");
        productName = ReadFromConsole.readFromConsole();
    }

    private void askForProductPrice() {

        System.out.println("\nProduct price: ");
        try {
            productPrice = Double.parseDouble(ReadFromConsole.readFromConsole());
        } catch (NullPointerException e) {
            System.out.println("The answer should be a number.");
            this.askForProductPrice();
        } catch (NumberFormatException e) {
            System.out.println("The answer should be a number.");
            this.askForProductPrice();
        }
    }

    private void askForShopName() {

        System.out.println("\nShop name: ");
        shopName = ReadFromConsole.readFromConsole();
    }

    public static void productsAvailable() {

        System.out.print("\nProducts available: ");

        PreparedStatement preparedStatement = null;
        Set<String> productsSet = new TreeSet<String>();

        try {
            preparedStatement = DatabaseConnection.getConnection().prepareStatement(Queries.selectAllProducts());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                productsSet.add(resultSet.getString(1));
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

        for (String entry : productsSet) {
            System.out.print(entry + " ");
        }
    }

    public static void shopsAvailable() {

        System.out.print("\nShops available: ");

        PreparedStatement preparedStatement = null;
        Set<String> productsSet = new TreeSet<String>();

        try {
            preparedStatement = DatabaseConnection.getConnection().prepareStatement(Queries.selectAllShops());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                productsSet.add(resultSet.getString(1));
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

        for (String entry : productsSet) {
            System.out.print(entry + " ");
        }
    }

    // Getters

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getShopName() {
        return shopName;
    }

    // Methods implemented from Table abstract class

    private List<String> rowContentsList = new ArrayList<String>();

    public void setEntriesFromResultSet(ResultSet resultSet) {

        try {
            date = resultSet.getString(1);
            time = resultSet.getString(2);
            productName = resultSet.getString(3);
            productPrice = resultSet.getDouble(4);
            shopName = resultSet.getString(5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void composeRowContentsList() {

        rowContentsList.add(date);
        rowContentsList.add(time);
        rowContentsList.add(productName);
        rowContentsList.add(String.valueOf(productPrice));
        rowContentsList.add(shopName);
    }

    public void showTableOneRow() {

        for (String entry : rowContentsList) {
            System.out.print(entry + "\t");
        }
        System.out.print("\n");
    }

    public void askForValues() {
        this.askForProductName();
        this.askForProductPrice();
        this.askForShopName();
    }

    public String getValues() {

        return "CURRENT_DATE, CURRENT_TIME, ?, ?, ?";
    }

    public PreparedStatement prepareAddRowStatement(PreparedStatement preparedStatement) {

        try {
            preparedStatement.setString(1, this.getProductName());
            preparedStatement.setDouble(2, this.getProductPrice());
            preparedStatement.setString(3, this.getShopName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }
}
