package homeWork2.client.ui;

import homeWork2.client.domain.Client; // Импорт класса Client
import homeWork2.server.ui.ServerWindow; // Импорт класса ServerWindow

import javax.swing.*; // Импорт библиотек Swing для создания графического интерфейса
import java.awt.*; // Импорт библиотек AWT для работы с графикой
import java.awt.event.*; // Импорт классов для обработки событий

/**
 * Класс ClientGUI представляет собой графический интерфейс клиента чата.
 * Реализует интерфейс ClientView для взаимодействия с классом Client.
 */
public class ClientGUI extends JFrame implements ClientView {
    public static final int WIDTH = 400; // Ширина окна клиента
    public static final int HEIGHT = 300; // Высота окна

    private Client client; // Объект Client для взаимодействия с логикой клиента
    JTextArea log; // Текстовая область для отображения логов
    JTextField tfIPAddress, tfPort, tfLogin, tfMessage; // Текстовые поля для ввода информации
    JPasswordField password; // Поле для ввода пароля
    JButton btnLogin, btnSend; // Кнопки для управления клиентом
    JPanel headerPanel; // Панель для размещения элементов заголовка

    /**
     * конструктор класса ClientGUI.
     * @param server Объект ServerWindow, представляющий сервер чата.
     */
    public ClientGUI(ServerWindow server){
        setting(server); // Вызов метода для настройки параметров окна и инициализации клиента
        createPanel(); // Вызов метода для создания графического интерфейса

        setVisible(true); // Отображение окна
    }

    /**
     * Метод для настройки параметров окна и инициализации клиента.
     * @param serverWindow Объект ServerWindow, представляющий сервер чата.
     */
    private void setting(ServerWindow serverWindow) {
        setSize(WIDTH, HEIGHT); // Установка размеров окна
        setResizable(false); // Запрет изменения размеров окна
        setTitle("Chat client"); // Установка заголовка окна
        setLocation(serverWindow.getX() - 500, serverWindow.getY()); // Установка позиции окна относительно окна сервера
        setDefaultCloseOperation(HIDE_ON_CLOSE); // Установка операции закрытия окна (скрытие окна)
        client = new Client(this, serverWindow.getConnection()); // Создание экземпляра класса Client, передавая текущий объект ClientGUI  и объект подключения к серверу
    }

    /**
     * Метод для отображения сообщения в текстовой области логов.
     * Реализация интерфейса ClientView.
     * @param msg Сообщение для отображения.
     */
    public void showMessage(String msg) {
        log.append(msg); // Добавление сообщения в текстовую область логов
    }

    /**
     * Метод для отключения от сервера.
     * Реализация интерфейса ClientView.
     */
    public void disconnectFromServer() {
        hideHeaderPanel(true); // Скрытие панели заголовка (с полями ввода)
        client.disconnectFromServer(); // Вызов метода disconnectFromServer у объекта Client
    }

    /**
     * метод для управления видимостью панели заголовка.
     * @param visible true, если панель должна быть видимой, false - если скрытой.
     */
    private void hideHeaderPanel(boolean visible){
        headerPanel.setVisible(visible); // Установка видимости панели заголовка
    }

    /**
     * Метод обработки события входа в чат.
     */
    private void login(){
        if (client.connectToServer(tfLogin.getText())) { // Попытка подключения к серверу с использованием введенного логина
            headerPanel.setVisible(false); // Скрытие панели заголовка после успешного подключения
        }
    }

    /**
     * Метод для отправки сообщения.
     */
    private void message(){
        client.message(tfMessage.getText()); // Отправка сообщения на сервер с использованием текста из текстового поля
        tfMessage.setText(""); // Очистка текстового поля после отправки сообщения
    }

    /**
     * Метод для создания панели с кампонентами графического интерфейса.
     */
    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH); // Добавление панели заголовка в северную часть окна
        add(createLog()); // Добавление текстовой области для отображения логов
        add(createFooter(), BorderLayout.SOUTH); // Добавление панели для ввода сообщения и кнопки отправки
    }

    /**
     * Метод для создания панели заголовка с полями ввода и кнопкой входа.
     * @return Компонент Component - панель заголовка.
     */
    private Component createHeaderPanel(){
        headerPanel = new JPanel(new GridLayout(2, 3)); // Создание панели заголовка с использованием GridLayout
        tfIPAddress = new JTextField("127.0.0.1"); // Создание текстового поля для ввода IP-адреса, инициализация значением по умолчанию
        tfPort = new JTextField("8189"); // Создание текстового поля для ввода порта, инициализация значением по умолчанию
        tfLogin = new JTextField("Ivan Ivanovich"); // Создание текстового поля для ввода логина, инициализация значением по умолчанию
        password = new JPasswordField("123456"); // Создание поля пароля, инициализация значением по умолчанию
        btnLogin = new JButton("login"); // Создание кнопки "login"
        btnLogin.addActionListener(new ActionListener() { // Добавление слушателя событий для кнопки "login"
            @Override
            public void actionPerformed(ActionEvent e) {
                login(); // Вызов метода login при нажатии кнопки
            }
        });

        headerPanel.add(tfIPAddress); // Добавление текстового поля IP-адреса на панель заголовка
        headerPanel.add(tfPort); // Добавление текстового поля порта на панель заголовка
        headerPanel.add(new JPanel()); // Добавление пустой панели для выравнивания
        headerPanel.add(tfLogin); // Добавление текстового поля логина на панель заголовка
        headerPanel.add(password); // Добавление поля пароля на панель заголовка
        headerPanel.add(btnLogin); // Добавление кнопки "login" на панель заголовка

        return headerPanel; // Возвращение панели заголовка
    }

    /**
     * Метод для создания текстовой области для отображения логов.
     * @return Компонент JScrollPane - прокручиваемая текстовая область.
     */
    private Component createLog(){
        log = new JTextArea(); // Создание текстовой области
        log.setEditable(false); // Установка флага, запрещающего редактирование текстовой области
        return new JScrollPane(log); // Возвращение прокручиваемой текстовой области
    }

    /**
     * Метод для создания нижней панели с полем ввода сообщения и кнопкой отправки.
     * @return Компонент Component - панель ввода сообщения и кнопки отправки.
     */
    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout()); // Создание панели с использованием BorderLayout
        tfMessage = new JTextField(); // Создание текстового поля для ввода сообщения
        tfMessage.addKeyListener(new KeyAdapter() { // Добавление слушателя событий клавиатуры к текстовому полю
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n'){ // Если нажата клавиша Enter
                    message(); // Вызов метода отправки сообщения
                }
            }
        });
        btnSend = new JButton("send"); // Создание кнопки "send"
        btnSend.addActionListener(new ActionListener() { // Добавление слушателя событий для кнопки "send"
            @Override
            public void actionPerformed(ActionEvent e) {
                message();  // Вызов метода отправки сообщения
            }
        });
        panel.add(tfMessage); // Добавление текстового поля на панель
        panel.add(btnSend, BorderLayout.EAST); // Добавление кнопки отправки в восточную часть панели
        return panel; // Возвращение нижней панели
    }

    /**
     * Переопределение метода processWindowEvent для обработки событий окна.
     * @param e Объект WindowEvent, содержащий информацию о событии окна.
     */
    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) { // Если происходит событие закрытия окна
            disconnectFromServer(); // Вызываем метод disconnectFromServer для отключения от сервера
        }
        super.processWindowEvent(e); // Вызываем метод родительского класса для обработки остальных событий окна
    }

}
