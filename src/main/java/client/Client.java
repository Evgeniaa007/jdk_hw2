package client;

import server.Server;


public class Client {
    private Server server;
    private boolean connected;
    private String name;
    ClientView view; // для связки с ClientGUI

    public Client(ClientView view, Server server){
        this.view = view;
        this.server = server;
    }

    // подключает пользователя при условии работы сервера
    public boolean connectToServer(String name){
        this.name = name;
        if(server.loginClient(this)){
            connected = true;
            topPanel.setVisible(false);
            printText("Вы успешно подключились!");
            name = loginField.getText();
            server.printText(name + " успешно подключился к беседе.");
            String log = serverWindow.getLog();
            if (log != null){
                printText(log);
            }
            return true;
        }
        else {
            printText("Подключение не удалось");
            return false;
        }
    }

    public void disconnect(){
        if(connected){
            connected = false;
            view.disconnectFromServer();
            server.disconnectClient(this);
            printText("Вы были отключены от сервера");
        }
    }
//мы посылаем
    public void answer(String message){
        if(connected){
            if(!message.isEmpty()){
                server.sendMessage(name + ": " + message);
             //   String message = messageField.getText()
                // messageField.setText("");
            }

        }
        else {
            printText("Вы не подключены к серверу. Для отправки сообщений произведите подключение.");
        }
    }
// нам посылают
    private void printText(String text){
        view.sendMessage(text);
    }

    public void serverAnswer(String answer){
        printText(answer);
    }
}
