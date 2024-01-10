package homeWork1.client;

import homeWork1.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ClientGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private ServerWindow server; // объект сервер
    private boolean connected;
    private String name;
    JTextArea log;

    // private final JPanel panelTop = new JPanel(new GridLayout(2,3));

    JTextField tfIPAddress; // private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    JTextField tfPort; // private final JTextField tfPort = new JTextField("8189");
    JTextField tfLogin; // private final JTextField tfLogin = new JTextField("Uncle Sam");
    JTextField tfMessage; // private final JTextField tfMessage = new JTextField();
    JPasswordField tfPassword; // private final JPasswordField tfPassword = new JPasswordField("123456");
    JButton btnLogin; // private final JButton btnLogin = new JButton("login");
    JButton btnSend; // private final JButton btnSend = new JButton("Send");
    JPanel headerPanel; //private final JPanel headerPanel = new JPanel(new BorderLayout());


    public ClientGUI(ServerWindow server) {
        this.server = server;

        setSize(WIDTH, HEIGHT);
        setResizable(false); // измение размеров отключили
        setTitle("Chat client");
        setLocation(server.getX() - 500, server.getY());

        createPanel(); // создание панели на которой будут отображатся виджиты

        setVisible(true);
    }

    public void answer (String text){
        appendLog(text);
    }
    private void connectToServer () {
        if (server.connectUser(this)) {
            appendLog("Вы успешно подключились! \n");
            headerPanel.setVisible(false);
            connected = true;
            name = tfLogin.getText();
            String log = server.getLog();
            if (log != null) {
                appendLog(log);
            }
        } else {
            appendLog("Подключение не удалось");
        }
    }
    public void disconnectFromServer () {
        if (connected) {
            headerPanel.setVisible(true);
            connected = false;
            server.disconnectUser(this);
            appendLog("Вы были отключены от сервера!");
        }
    }
    public void message() {
        if (connected) {
            String text = tfMessage.getText();
            if (!text.equals("")) {
                server.message(name + ": " + text);
                tfMessage.setText("");
            }
        } else {
            appendLog("Нет подключения к серверу");
        }
    }
    private void appendLog(String text) {
        log.append(text + "\n");
    }

    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }
    private Component createHeaderPanel() {
        headerPanel = new JPanel(new GridLayout(2, 3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Micky Mouse");
        tfPassword = new JPasswordField("123456");
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });

        headerPanel.add(tfIPAddress);
        headerPanel.add(tfPort);
        headerPanel.add(new JPanel());
        headerPanel.add(tfLogin);
        headerPanel.add(tfPassword);
        headerPanel.add(btnLogin);

        return headerPanel;
    }

    private Component createLog() {
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }
    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    message();
                }
            }
        });

        btnSend = new JButton("send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });
        panel.add(tfMessage);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
        }

        @Override
        protected void processWindowEvent (WindowEvent e){
            super.processWindowEvent(e);
            if (e.getID() == WindowEvent.WINDOW_CLOSING) {
                disconnectFromServer();
            }
            super.processWindowEvent(e);
        }
}
