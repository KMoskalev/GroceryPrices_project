package launcher;

import database.DatabaseConnection;
import menu.Menu;

public class Launcher {
    public static void main(String[] args) {

        DatabaseConnection.newConnection();

        Menu.askWhatToDo();
    }
}
