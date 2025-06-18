package homeWork2.server.domain;

import homeWork2.client.domain.Client; // Импорт класса Client
import homeWork2.server.repository.Repository; // Импорт класса Repository
import homeWork2.server.ui.ServerView; // Импорт класса ServerView

import java.util.ArrayList; // Импорт класса ArrayList
import java.util.List; // Импорт интерфейса List

/**
 * Класс Server представляет собой сервер чата.
 * Он отвечает за управление клиентами, отправку сообщений и хранение истории сообщений.
 */
public class Server {
    private boolean work; // Флаг, указывающий, работает ли сервер
    private ServerView serverView; // Объект ServerView для взаимодействия с графическим интерфейсом сервера
    private List<Client> clientList; // Список подключенных клиентов
    private Repository<String> repository; // Объект Repository для хранения истории сообщений

    /**
     * Конструктор класса Server.
     *
     * @param serverView Объект ServerView для взаимодействия с графическим интерфейсом сервера.
     * @param repository Объект Repository для хранения истории сообщений.
     */
    public Server(ServerView serverView, Repository<String> repository) {
        this.serverView = serverView; // Инициализация объекта ServerView
        this.repository = repository; // Инициализация объекта Repository
        clientList = new ArrayList<>(); // Создание списка для хранения подключенных клиентов
    }

    /**
     * Метод для запуска сервера.
     */
    public void start() {
        if (work) { // Если сервер уже запущен
            showOnWindow("Сервер уже был запущен"); // Отображение сообщения о том, что сервер уже запущен
        } else { // Если сервер не запущен
            work = true; // Установка флага работы сервера в true
            showOnWindow("Сервер запущен!"); // Отображение сообщения о запуске сервера
        }
    }

    /**
     * Метод для остановки сервера.
     */
    public void stop() {
        if (!work) { // Если сервер уже остановлен
            showOnWindow("Сервер уже был остановлен"); // Отображение сообщения о том, что сервер уже остановлен
        } else { // Если сервер работает
            work = false; // Установка флага работы сервера в false
            for (Client client : clientList) { // Перебор всех подключенных клиентов
                disconnectUser(client); // Отключение клиента от сервера
            }
            showOnWindow("Сервер остановлен!"); // Отображение сообщения об остановке сервера
        }
    }

    /**
     * Метод для отключения пользователя от сервера.
     *
     * @param client Объект Client, представляющий отключаемого клиента.
     */
    public void disconnectUser(Client client) {
        clientList.remove(client); // Удаление клиента из списка подключенных клиентов
        if (client != null) { // Если клиент существует
            client.disconnectFromServer(); // Отключение клиента от сервера
            showOnWindow(client.getName() + " отключился от беседы"); // Отображение сообщения об отключении клиента
        }
    }

    /**
     * Метод для подключения пользователя к серверу.
     *
     * @param client Объект Client, представляющий подключаемого клиента.
     * @return true, если подключение успешно, false - если подключение не удалось.
     */
    public boolean connectUser(Client client) {
        if (!work) { // Если сервер не работает
            return false; // Возврат false, если подключение не удалось
        }
        clientList.add(client); // Добавление клиента в список подключенных клиентов
        showOnWindow(client.getName() + " подключился к беседе"); // Отображение сообщения о подключении клиента
        return true; // Возврат true, если подключение успешно
    }

    /**
     * Метод для отправки сообщения всем подключенным клиентам.
     *
     * @param text Текст сообщения для отправки.
     */
    public void message(String text) {
        if (!work) { // Если сервер не работает
            return; // Выход из метода, если сервер не работает
        }
        showOnWindow(text); // Отображение сообщения в окне сервера
        answerAll(text); // Отправка сообщения всем подключенным клиентам
        saveInHistory(text); // Сохранение сообщения в историю
    }

    /**
     * Метод для получения истории сообщений.
     *
     * @return Строка, содержащая историю сообщений.
     */
    public String getHistory() {
        return repository.load(); // Загрузка истории сообщений из репозитория
    }

    /**
     * Метод для отправки сообщения всем подключенным клиентам.
     *
     * @param text Текст сообщения для отправки.
     */
    private void answerAll(String text) {
        for (Client client : clientList) { // Перебор всех подключенных клиентов
            client.answerFromServer(text); // Отправка сообщения клиенту
        }
    }

    /**
     * Метод для отображения текста в окне сервера.
     *
     * @param text Текст для отображения.
     */
    private void showOnWindow(String text) {
        serverView.showMessage(text + "\n"); // Отображение текста в окне сервера с добавлением символа новой строки
    }

    /**
     * Метод для сохранения сообщения в историю.
     *
     * @param text Текст сообщения для сохранения.
     */
    private void saveInHistory(String text) {
        repository.save(text); // Сохранение сообщения в репозиторий
    }
}

