package homeWork1;

import homeWork1.client.ClientGUI;
import homeWork1.server.ServerWindow;

public class Main {
    public static void main(String[] args){
        ServerWindow serverWindow = new ServerWindow(); // объект сервера
        new ClientGUI(serverWindow); // объект сервера передаем 2-м клиентским приложения для того чтобы они могли общатся между собой
        new ClientGUI(serverWindow);
    }
}
