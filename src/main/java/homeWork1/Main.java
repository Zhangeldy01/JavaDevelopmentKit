package homeWork1;

import homeWork1.client.ClientGUI;
import homeWork1.server.ServerWindow;

public class Main {
    public static void main(String[] args){
        ServerWindow serverWindow = new ServerWindow(); // Объект сервера
        new ClientGUI(serverWindow); // объект сервера передаем 2-м клиентским приложениям для того, чтобы они могли общаться между собой
        new ClientGUI(serverWindow);
    }
}
