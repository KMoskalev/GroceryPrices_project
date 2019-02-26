package queries;

import table_classes.Table;

public class Queries {

    // SELECT

    private static String selectAllQuery = "SELECT * FROM " + Table.getCurrentTableName();
    private static String selectAllProductsQuery = "SELECT product FROM " + Table.getCurrentTableName();
    private static String selectAllShopsQuery = "SELECT shop FROM " + Table.getCurrentTableName();

    public static String selectAll() {
        return selectAllQuery + ";";
    }

    public static String selectAllProducts() {
        return selectAllProductsQuery + ";";
    }

    public static String selectAllShops() {
        return selectAllShopsQuery + ";";
    }

    public static String selectSpecificProduct(String productName) {
        return selectAllQuery + " WHERE product=\'" + productName + "\';";
    }

    public static String selectSpecificShop(String shopName) {
        return selectAllQuery + " WHERE shop=\'" + shopName + "\';";
    }

    public static String selectSpecificProductFromShop(String productName, String shopName) {
        return selectAllQuery
                + " WHERE product=\'" + productName + "\'"
                + " AND shop=\'" + shopName + "\';";
    }

    // INSERT

    private static String insertValuesQuery = "INSERT INTO " + Table.getCurrentTableName();

    public static String insertValues(Table tableRow) {
        return insertValuesQuery + " VALUES(" + tableRow.getValues() + ");";
    }
}
