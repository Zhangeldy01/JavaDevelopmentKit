package homeWork2;

import homeWork2.server.ui.ServerWindow;
import homeWork2.client.ui.ClientGUI;

/**
 * Класс Main - точка входа в приложение.
 * Создает экземпляр ServerWindow и несколько экземпляров ClientGUI.
 */
public class Main {

    /**
     * Основной метод, выполняемый при запуске приложения.
     * @param args Аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow(); // Создание экземпляра ServerWindow (окна сервера)
        new ClientGUI(serverWindow); // Создание первого экземпляра ClientGUI (окна клиента)
        new ClientGUI(serverWindow); // Создание второго экземпляра ClientGUI (окна клиента)
    }
}
