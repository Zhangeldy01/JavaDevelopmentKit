package homeWork1.client;

import homeWork1.server.ServerWindow;

import javax.swing.*; // Импорт библиотек Swing для создания графического интерфейса
import java.awt.*; // Импорт библиотек AWT  для работы с графикой
import java.awt.event.*;

/**
 * Класс ClientGUI - это графический интерфейс клиента чата.
 * Он позволяет пользователю подключаться к серверу, отправлять сообщения и просматривать логи.
 */
public class ClientGUI extends JFrame {
    private static final int WIDTH = 400; // Ширина окна клиента
    private static final int HEIGHT = 300; // Высота окна клиента

    private ServerWindow server; // Объект  ServerWindow (ссылка на сервер)
    private boolean connected; // Флаг, указывающий, подключен ли клиент к серверу
    private String name; // Имя клиента
    JTextArea log; // Текстовая область для отображения логов

    // private final JPanel panelTop = new JPanel(new GridLayout(2,3));

    JTextField tfIPAddress; // Текстовое поле для ввода IP-адреса сервера
    JTextField tfPort; // Текстовое поле для ввода порта сервера
    JTextField tfLogin; // Текстовое поле для ввода логина
    JTextField tfMessage; // Текстовое поле для ввода сообщения
    JPasswordField tfPassword; // Поле для ввода пароля
    JButton btnLogin; // Кнопка для подключения к серверу
    JButton btnSend; // Кнопка для отправки сообщения
    JPanel headerPanel; // Панель для размещения полей ввода и кнопок подключения


    /**
     * Конструктор класса ClientGUI.
     * @param server Объект ServerWindow (ссылка на сервер).
     */
    public ClientGUI(ServerWindow server) {
        this.server = server; // Сохраняем ссылку на сервер

        setSize(WIDTH, HEIGHT); // Устанавливаем размеры окна
        setResizable(false); // Запрещаем изменение размеров окна
        setTitle("Chat client"); // Устанавливаем заголовок окна
        setLocation(server.getX() - 500, server.getY()); // Размещаем окно относительно окна сервера

        createPanel(); // Создаем панель на которой будут отображатся виджиты

        setVisible(true); // Отображаем окно
    }

    /**
     * Метод для отображения сообщения в текстовой области логов.
     * @param text Текст сообщения для отображения.
     */
    public void answer (String text){
        appendLog(text); // Добавляем сообщение в текстовую область логов
    }

    /**
     * Метод для подключения к серверу.
     */
    private void connectToServer () {
        if (server.connectUser(this)) { // Пытаемся подключиться к серверу
            appendLog("Вы успешно подключились! \n"); // Выводим сообщение об успешном подключении
            headerPanel.setVisible(false); // Скрываем панель с полями ввода и кнопкой подключения
            connected = true; // Устанавливаем флаг подключения
            name = tfLogin.getText(); // Получаем имя пользователя из текстового поля
            String log = server.getLog(); // Получаем логи сервера
            if (log != null) { // Если логи есть
                appendLog(log); // Выводим логи в текстовую область
            }
        } else { // Если подключение не удалось
            appendLog("Подключение не удалось"); // Выводим сообщение об ошибке
        }
    }

    /**
     * Метод для отключения от сервера
     */
    public void disconnectFromServer () {
        if (connected) { // Если клиент подключен к серверу
            headerPanel.setVisible(true); // Отображаем панель с полями ввода и кнопкой подключения
            connected = false; // Устанавливаем флаг подключения в false
            server.disconnectUser(this); // Отключаем клиента от сервера
            appendLog("Вы были отключены от сервера!"); // Выводим сообщение об отключении
        }
    }

    /**
     * Метод для отправки сообщения на сервер.
     */
    public void message() {
        if (connected) { // Если клиент подключен к серверу
            String text = tfMessage.getText(); // Получаем текст сообщения из текстового поля
            if (!text.equals("")) { // Если текст сообщения не пустой
                server.message(name + ": " + text); // Отправляем сообщение на сервер
                tfMessage.setText(""); // Очищаем текстовое поле
            }
        } else { // Если клиент не подключен к серверу
            appendLog("Нет подключения к серверу"); // Выводим сообщение об отсутствии подключения
        }
    }

    /**
     * Метод для добавления сообщения в текстовую область логов.
     * @param text Текст сообщения для добавления.
     */
    private void appendLog(String text) {
        log.append(text + "\n"); // Добавляем текст сообщения и символ новой строки в текстовую область
    }

    /**
     * Метод для создания и размещения компонентов на панели.
     */
    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH); // Добавляем верхнюю панель с полями ввода и кнопкой
        add(createLog()); // Добавляем текстовую область для отображения логов
        add(createFooter(), BorderLayout.SOUTH); // Добавляем нижнюю панель с полем ввода сообщения и кнопкой отправки
    }

    /**
     * Метод для создания верхней панели с полями ввода и кнопкой подключения.
     * @return Компонент JPanel, содержащий поля ввода и кнопку подключения.
     */
    private Component createHeaderPanel() {
        headerPanel = new JPanel(new GridLayout(2, 3)); // Создаем панель с сеткой 2х3
        tfIPAddress = new JTextField("127.0.0.1"); // Создаем текстовое поле для IP-адреса
        tfPort = new JTextField("8189"); // Создаем текстовое поле для порта
        tfLogin = new JTextField("Micky Mouse"); // Создаем текстовое поле для логина
        tfPassword = new JPasswordField("123456"); // Создаем поле пароля
        btnLogin = new JButton("login"); // Создаем кнопку "login"
        btnLogin.addActionListener(new ActionListener() { // Добавляем ActionListener для кнопки "login"
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer(); // Вызываем метод подключения к серверу при нажатии кнопки
            }
        });

        headerPanel.add(tfIPAddress); // Добавляем поле IP-адреса на панель
        headerPanel.add(tfPort); // Добавляем поле порта на панель
        headerPanel.add(new JPanel()); // Добавляем пустую панель для выравнивания
        headerPanel.add(tfLogin); // Добавляем поле логина на панель
        headerPanel.add(tfPassword); // Добавляем поле пароля на панель
        headerPanel.add(btnLogin); // Добавляем кнопку "login" на панель

        return headerPanel; // Возвращаем созданную панель
    }

    /**
     * Метод для создания текстовой области для отображения логов.
     * @return Компонент JScrollPane, содержащий текстовую область логов.
     */
    private Component createLog() {
        log = new JTextArea(); // Создаем текстовую область
        log.setEditable(false); // Запрещаем редактирование текстовой области
        return new JScrollPane(log); // Возвращаем JScrollPane с текстовой областью
    }

    /**
     * Метод для создания нижней панели с полем ввода сообщения и кнопкой отправки.
     * @return Компонент JPanel, содержащий поле ввода сообщения и кнопку отправки.
     */
    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout()); // Создаем панель с BorderLayout
        tfMessage = new JTextField(); // Создаем текстовое поле для ввода сообщения
        tfMessage.addKeyListener(new KeyAdapter() { // Добавляем KeyListener для текстового поля
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') { // Если нажата клавиша Enter
                    message(); // Вызываем метод отправки сообщения (1 способ отправки сообщения)
                }
            }
        });

        btnSend = new JButton("send"); // Создаем кнопку "send"
        btnSend.addActionListener(new ActionListener() { // Добавляем ActionListener для кнопки "send"
            @Override
            public void actionPerformed(ActionEvent e) {
                message(); // Вызываем метод отправки сообщения при нажатии кнопки (2 способ отправки сообщения)
            }
        });
        panel.add(tfMessage); // Добавляем текстовое поле на панель
        panel.add(btnSend, BorderLayout.EAST); // Добавляем кнопку "send" в восточную часть панели
        return panel; // Возвращаем созданную панель
        }

    /**
     * Переполненный метод для обработки событий окна.
     * @param e  Объект WindowEvent, содержащий информацию о событии окна.
     */
    @Override
        protected void processWindowEvent (WindowEvent e){
            super.processWindowEvent(e); // Вызываем метод родительского класса
            if (e.getID() == WindowEvent.WINDOW_CLOSING) { // Если окно закрывается
                disconnectFromServer(); // Вызываем метод отключения от сервера
            }
            super.processWindowEvent(e); // Вызываем метод родительского класса
        }
}
