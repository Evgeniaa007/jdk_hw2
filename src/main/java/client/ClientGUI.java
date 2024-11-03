package client;

import server.Server;
import server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements ClientView {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private JPanel topPanel;
    private JPanel bottomPanel;
    private JTextField loginField, ipField, portField, messageField;
    private JPasswordField passwField;
    private JTextArea log;
    private JButton btnLogin, btnSend;

    private Client client;


    public ClientGUI(Server server) {
        windowSettings();
        createClientWin();
        setVisible(true);
    }

    private void windowSettings(){
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
    }

    //верхняя часть окна клиента
    private Component createTopPanel(){
        topPanel = new JPanel(new GridLayout(2, 3));
        ipField = new JTextField ("127.0.0.1");
        portField = new JTextField ("8189");
        loginField = new JTextField ("Ivan_Igorevich");
        passwField = new JPasswordField ("123456");
        btnLogin = new JButton("Login");

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });

        topPanel.add(ipField);
        topPanel.add(portField);
        topPanel.add(loginField);
        topPanel.add(passwField);
        topPanel.add(btnLogin);

        return topPanel;
    }

    //окно вывода сообщений
    private Component createLog(){
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    public void appendLog(String text){
        log.append(text + "\n");
    }

    //нижняя часть окна клиента, связанная с наббором и отправкой сообщений
    private Component createBottomPanel(){
        bottomPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        messageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
        btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        messageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == '\n'){
                    sendMessage();
                }
            }
        });

        bottomPanel.add(messageField);
        bottomPanel.add(btnSend, BorderLayout.EAST);

        return bottomPanel;
    }

    // формирование окна клиента
    private void createClientWin(){
        add(createTopPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private void sendMessage(){
        client.answer(messageField.getText());
        messageField.setText("");

    }

    // подключает пользователя при условии работы сервера
    private void connectToServer(){
        if(client.connectToServer(loginField.getText())){
            topPanel.setVisible(false); //hideTopPanel
        }
    }

    public void disconnectFromServer() {
        topPanel.setVisible(true);
        client.disconnect();
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING){
            disconnected();
        }
    }

    @Override
    public void sendMessage(String message) {
       appendLog(message);
    }

    @Override
    public void disconnected() {
        topPanel.setVisible(true);
    }

    @Override
    public void setClientController(Client client) {
        this.client = client;
    }

}
