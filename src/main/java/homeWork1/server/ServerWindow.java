package homeWork1.server;

import homeWork1.client.ClientGUI;

import javax.swing.*; // Импорт библиотек Swing для создания графического интерфейса
import java.awt.*; // Импорт библиотек AWT для раобы с графикой
import java.awt.event.ActionEvent; // Импорт класса ActionEvent  для обработки событий
import java.awt.event.ActionListener; // Импорт интерфейса ActionListener для обработки действий пользователя
import java.io.FileReader; // Импорт библиотек для работы с вводом (файлами)
import java.io.FileWriter; // Импорт библиотек для работы с выводом (файлами)
import java.util.ArrayList; // Импорт класса ArrayList для создания динамического списка
import java.util.List; // Импорт интерфейса List для работы со списками


/**
 * Класс ServerWindow - это главное окно сервера чата.
 * Он отвечает за запуск и остановку сервера, отображение логов и управление подключенными клиентами.
 */

public class ServerWindow extends JFrame {
    // private static final int POS_X = 500;
    // private static final int POS_Y = 550;
    private static final int WIDTH = 400; // Ширина окна
    private static final int HEIGHT = 300; // Высота окна
    public static final String LOG_PATH = "src/main/java/homeWork1/server/log.txt";

    private List<ClientGUI> clientGUIList; // список подключенных клиентов
    private JButton btnStart; // Кнопка запуска сервера
    private JButton btnStop; // Кнопка остановки сервера
    private JTextArea log; // Текстовая область для отображения логов
    private boolean isServerWorking; // Флаг, указывающий, работает ли сервер


    /**
     * Конструктор класса ServerWindow.
     * Инициализирует окно сервера, создает панель управления и делает окно видимым.
     */
    public ServerWindow() {
        clientGUIList = new ArrayList<>(); // Создание списка для хранения подключенных клиентов

        setDefaultCloseOperation(EXIT_ON_CLOSE); // Установка операции закрытия окна (завершения приложения)
        setSize(WIDTH, HEIGHT); // Установка размеров окна
        setResizable(false); // Запрет изменения размеров окна
        setTitle("Chat server"); // Установка заголовка окна
        setLocationRelativeTo(null); // Размещение окна по центру экрана

        createPanel(); // Создание панели управления с кнопками и текстовой областью

        setVisible(true); // Отображение окна
    }

    /**
     * Метод для подключения нового клиента к серверу.
     * @param clientGUI Объект ClientGUI, представляющий подключенного клиента.
     * @return true, если клиент успешно подключен, false - если сервер не работает.
     */
    public boolean connectUser(ClientGUI clientGUI){
        if(!isServerWorking) { // Если сервер не работает
            return false; // Отклоняем подключение
        }
        else clientGUIList.add(clientGUI); // Добавляем клиента в список подключенных клиентов
        return true; // Подключение успешно

    }

    /**
     * Метод для получения логов сервера.
     * @return Строка, содержащая логи сервера.
     */
    public String getLog() {
       return readLog(); // Читаем логи из файла и возвращаем строку
    }

    /**
     * Метод для отключения клиента от сервера.
     * @param clientGUI Объукт clientGUI, представляющий отключаемого клиента.
     */
    public void disconnectUser(ClientGUI clientGUI){
        clientGUIList.remove(clientGUI); // Удаляем клиента из списка подключенных клиентов
        if (clientGUI != null) { // Если клиент существует
            clientGUI.disconnectFromServer(); // Отключаем клиента от сервера
        }
    }

    /**
     * Метод для работы с сообщением.
     * @param text Текст сообщения для отправки.
     */
    public void message(String text){
        if(!isServerWorking) { // Если сервер не работает
            return; // Ничего не делаем
        }
        text += ""; // Пустая опкрация (для избежания null pointer exception)
        appendLog(text); // Добавляем сообщение в лог
        answerAll(text); // Отправляем сообщение всем клиентам
        saveInLog(text); // Сохраняем сообщение в файл логов
    }

    /**
     * Метод для отправки сообщения всем подключенным клиентам.
     * @param text Текст сообщения для отправки.
     */
    private void answerAll(String text){
        for (ClientGUI clientGUI: clientGUIList) { // Перебираем всех клиентов в списке
            clientGUI.answer(text); // Отправляем сообщение клиенту
        }
    }

    /**
     * Метод для сохранения сообщения в файл логов.
     * @param text Текст сообщения для сохранения в лог.
     */
    private void saveInLog(String text){
        try (FileWriter writer = new FileWriter(LOG_PATH, true)) { // Открываем файл для записи в режиме добавления
            writer.write(text); // Записываем текст сообщения в файл
            writer.write("\n"); // Добавляем символ новой строки
        } catch(Exception e) { // Обрабатыаем возможные исключения
            e.printStackTrace(); // Выводим информацию об ошибке в консоль
        }
    }

    /**
     * Метод для чтения логов из файла.
     * @return Строка, содержащая логи сервера.
     */

    private String readLog() {
        StringBuilder stringBuilder = new StringBuilder(); // создаем StringDuilder для формирования строки с логами
        try (FileReader reader = new FileReader(LOG_PATH);) { // ткрываем файл для чтения
            int c; // Переменная для хранения символа, прочитанного из файла
            while((c = reader.read())!= -1){ // Читаем файл посимвольно до конца файла
                stringBuilder.append((char) c); // Добавляем символ в StringBuilder
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length()); // Удаляем последний символ
            return stringBuilder.toString(); // Возвращаем строку с логами
        } catch(Exception e){ // Обрабатываем возможные исключения
            e.printStackTrace(); // Выводим информацию об ошибке в консоль
            return  null; // Возвращаем null в случае ошибки
        }
    }

    /**
     * Метод для добавления сообщения в текстовую область логов.
     * @param text Текст сообщения для добавления в лог.
     */
    private void appendLog(String text){
        log.append(text + "\n"); // Добавляем текст сообщения и символ новой строки в текстовую область
    }

    /**
     * Метод для создания панели управления с кнопками и текстовой областью.
     */
    private void createPanel(){
        log = new JTextArea(); // Создаем текстовую область для отображения логов
        add(log); // Добавляем текстовую область в окно
        add(createButtons(), BorderLayout.SOUTH); // Добавляем панель с кнопками в нижнюю часть окна
    }

    /**
     * Метод для создания панели с кнопками управления сервером.
     * @return Компонент JPanel, содержащий кнопки "Start" и "Stop".
     */
    private Component createButtons(){
        JPanel panel = new JPanel(new GridLayout(1,2)); // Созаем панель с сеткой 1х2
        btnStart = new JButton("Start"); // Создаем кнопку "Start"
        btnStop = new JButton("Stop"); // Создаем кнопку "Stop"

        // Добавляем ActionListener для кнопки "Start"
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isServerWorking) { // Если сервер уже запущен
                    appendLog("сервер уже был запущен"); // Выводим сообщение в лог
                }else { // Если сервер не запущен
                    isServerWorking = true; // Устанавливаем флаг работы сервера в true
                    appendLog("Сервер запущен!"); // Выводим сообщение в лог
                }
            }
        });

        // Добавляем ActionListener для кнопки "Stop"
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isServerWorking) { // Если сервер уже остановлен
                    appendLog("Сервер был уже остановлен"); // Выводим сообщение в лог
                }else { // Если сервер работает
                    isServerWorking = false; // Устанавливаем флаг работы сервера в false
                    while(!clientGUIList.isEmpty()) { // Пока есть подключенные клиенты
                        disconnectUser(clientGUIList.get(clientGUIList.size() - 1)); // Отключаем последнего клиента из списка
                    }
                    appendLog("Сервер остановлен!"); // Выводим сообщение в лог
                }
            }
        });

        panel.add(btnStart); // Добавляем кнопку "Start" на панель
        panel.add(btnStop); // Добавляем кнопку "Stop" на панель
        return panel; // Возвращаем панель с кнопками
    }
}
