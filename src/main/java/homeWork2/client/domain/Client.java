package homeWork2.client.domain;

import homeWork2.client.ui.ClientView; // Импорт класса ClientView для отображения графического интерфейса клиента
import homeWork2.server.domain.Server; // Импорт класса Server для взаимодействия с сервером

/**
 * Класс Client представляет собой клиента чата.
 * Он отвечает за подключение к серверу, отправку сообщений и отображение информации в графическом интерфейсе.
 */
public class Client {
    private ClientView clientView; // Объект ClientView для взаимодействия с графическим интерфейсом клиента
    private String name; // Имя клиента
    private Server server; // Объект Server для взаимодействия с сервером
    private boolean connected; // Флаг, указывающий, подключен ли клиент к серверу

    /**
     * Конструктор класса Client.
     * @param clientView Объект ClientView для взаимодействия с графическим интерфейсом
     * @param server Объект Server для взаимодействия с сервером.
     */
    public Client(ClientView clientView, Server server) {
        this.clientView = clientView; // Инициализация объекта ClientView
        this.server = server; // Инициализация объекта Server
    }

    /**
     * Метод для подключения клиента к серверу.
     * @param name Имя клиента.
     * @return true, если подключение успешно, false - если подключение не удалось.
     */
    public boolean connectToServer(String name){
        this.name = name; // Установка имени клиента
        if (server.connectUser(this)){ // Попытка подключения к серверу
            showOnWindow("Вы успешно подключились!\n"); // Отображение сообщения об успешном подключении в окне клиента
            connected = true; // Установка флага подключения
            String log = server.getHistory(); // Получение истории сообщений с сервера
            if (log != null){ // Если история сообщений не пуста
                showOnWindow(log); // Отображение истории сообщений в окне клиента
            }
            return true; // Возврат true, если подключение успешно
        } else { // Если подключение не удалось
            showOnWindow("Подключение не удалось"); // Отображение сообщения об ошибке подключения в окне клиента
            return false; // Возврат false, если подключение не удалось
        }
    }

    /**
     * Метод для получения ответа от сервера.
     * @param text Текст сообщения, полученного от сервера.
     */
    public void answerFromServer(String text) {
        showOnWindow(text); // Отображение сообщения, полученного от сервера, в окне клиента
    }

    /**
     * Метод для отключения клиента от сервера.
     */
    public void disconnectFromServer(){
        if (connected) { // Если клиент подключен к серверу
            connected = false; // Установка флага подключения в false
            clientView.disconnectFromServer(); // Отключение клиента от графического интерфейса
            server.disconnectUser(this); // Отключение клиента от сервера
            showOnWindow("Вы были отключены от сервера!"); // отображение сообщения об отключении в окне клиента
        }
    }

    /**
     * Метод для отправки сообщения на сервер.
     * @param text Текст сообщения для отправки.
     */
    public void message(String text){
        if (connected) { // Если клиент подключен к серверу
            if (!text.isEmpty()) { // Если текст сообщения пустой
                server.message(name + ": " + text); // Отправка сообщения на сервер с указанием имени клиента
            }
        } else { // Если клиент не подключен к серверу
            showOnWindow("Нет подключения к серверу"); // Отображение сообщения об отсутствии подключения в окне клиента
        }
    }

    /**
     * Метод для получения имени клиента.
     * @return Имя клиента.
     */
    public String getName() {
        return name; // Возврат имени клиента
    }

    /**
     * Метод для отображения текста в окне клиента.
     * @param text Текст для отображения.
     */
    private void showOnWindow(String text) {
        clientView.showMessage(text + "\n"); // Отображение текста в окне клиента с добавлением символа новой строки
    }
}
