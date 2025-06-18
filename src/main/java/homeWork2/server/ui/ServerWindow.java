package homeWork2.server.ui;

import homeWork2.server.domain.Server; // Импорт класса Server
import homeWork2.server.repository.FileStorage; // Импорт класса FileStorage

import javax.swing.*; // Импорт библиотек Swing для создания графического интерфейса
import java.awt.*; // Импорт библиотек AWT для работы с графикой
import java.awt.event.ActionEvent; // Импорт класса ActionEvent для обработки событий
import java.awt.event.ActionListener; // Импорт интерфейса ActionListener для обработки действий пользователя


/**
 * Класс ServerWindow представляет собой графический интерфейс сервера чата.
 * Реализует интерфейс ServerView для отображения сообщений и предоставляет функциональность управления сервером.
 */
public class ServerWindow extends JFrame implements ServerView {
    private static final int WIDTH = 400; // Ширина окна сервера
    private static final int HEIGHT = 300; // Высота окна сервера
    // private static final String LOG_PATH = "src/main/java/homeWork2/server/log.txt"; // Путь к файлу логов (закомментирован)

    // private List<ClientGUI> clientGUIList; // Список интерфейсов клиентов (закомментировано)

    private JButton btnStart, btnStop; // Кнопки для запуска и остановки сервера
    private JTextArea log; // Текстовая область для отображения логов
    // private boolean work; // Флаг, указывающий, работает ли сервер (закомментировано, теперь в Server)
    private Server server; // Объект Server для взаимодействия с логикой сервера

    /**
     * Конструктор класса ServerWindow.
     * Инициализирует графический интерфейс сервера.
     */
    public ServerWindow() {
        setting(); // Настройка параметров окна
        createPanel(); // Создание компонентов графического интерфейса

        setVisible(true); // Отображение окна
    }

    /**
     * Метод для настройки параметров окна.
     */
    private void setting() {
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Установка операции закрытия окна (завершение работы приложения)
        setSize(WIDTH, HEIGHT); // Установка размеров окна
        setResizable(false); // Запрет изменения размеров окна
        setTitle("Chat server"); // Установка заголовка окна
        setLocationRelativeTo(null); // Размещение окна по центру экрана
        server = new Server(this, new FileStorage()); // Создание экземпляра класса Server и передача текущего объекта ServerWindow и экземпляра FileStorage
    }

    /**
     * Метод для получения доступа к серверу (объекту Server).
     *
     * @return Объект Server.
     */
    public Server getConnection() {
        return server; // Возвращение объекта Server
    }

    /**
     * Метод для создания панели с компонентами графического интерфейса.
     */
    private void createPanel() {
        log = new JTextArea(); // Создание текстовой области для отображения логов
        add(log); // Добавление текстовой области в окно
        add(createButtons(), BorderLayout.SOUTH); // Добавление панели с кнопками в нижнюю часть окна
    }

    /**
     * Метод для создания панели с кнопками управления сервером.
     *
     * @return Компонент Component - панель с кнопками.
     */
    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2)); // Создание панели с использованием GridLayout
        btnStart = new JButton("Start"); // Создание кнопки "Start"
        btnStop = new JButton("Stop"); // Создание кнопки "Stop"

        btnStart.addActionListener(new ActionListener() { // Добавление слушателя событий для кнопки "Start"
            @Override
            public void actionPerformed(ActionEvent e) {
                server.start(); // Вызов метода start() объекта server при нажатии кнопки
            }
        });

        btnStop.addActionListener(new ActionListener() { // Добавление слушателя событий для кнопки "Stop"
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stop(); // Вызов метода stop() объекта server при нажатии кнопки
            }
        });

        panel.add(btnStart); // Добавление кнопки "Start" на панель
        panel.add(btnStop); // Добавление кнопки "Stop" на панель
        return panel; // Возвращение созданной панели
    }

    /**
     * Реализация метода showMessage интерфейса ServerView для отображения сообщений в логе.
     *
     * @param msg Сообщение для отображения.
     */
    @Override
    public void showMessage(String msg) {
        log.append(msg); // Добавление сообщения в текстовую область логов
    }
}
