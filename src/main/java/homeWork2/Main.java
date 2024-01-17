package homeWork2;

import homeWork2.server.ui.ServerWindow;
import homeWork2.client.ui.ClientGUI;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientGUI(serverWindow);
        new ClientGUI(serverWindow);
    }
}
