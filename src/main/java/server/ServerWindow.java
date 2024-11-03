package server;

import client.ClientGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ServerWindow extends JFrame implements ServerView{

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private JButton btnStart, btnStop;
    private JTextArea log;
    private Server server;

    public ServerWindow() {
        server = new Server(this);
        ServerWindowSettings();
        createServerWin();
        setVisible(true);
    }

    private void ServerWindowSettings(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Chat Server");
    }

    //Метод сборки окна сервера
    private void createServerWin(){
        log = new JTextArea();
        log.setEditable(false);
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }


    // Метод, который создает кнопки запуска и остановки на начальном окне
    private Component createButtons(){
        JPanel serverW = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.connect();
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.disconnect();
            }
        });
        serverW.add(btnStart);
        serverW.add(btnStop);
        return serverW;
    }

    @Override
    public void sendMessage(String message) {
        log.append(message + "\n");
    }

    @Override
    public void setServer(Server server) {
        this.server = server;
    }
}
