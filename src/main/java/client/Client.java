package client;

import server.Server;


public class Client {
    private Server server;
    private boolean connected;
    private String login;
    ClientView view; // для связки с ClientGUI

    public Client(ClientView view, Server server){
        this.view = view;
        this.server = server;
        view.setClientController(this);
    }

    public String getLogin(){
        return login;
    }

    // подключает пользователя при условии работы сервера
    public boolean connectToServer(String login){
        this.login = login;
        if(server.loginClient(this)){
            connected = true;
            printText("Вы успешно подключились!");
            server.printText(getLogin() + " успешно подключился к беседе.");
            String log = server.getLog();
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
            view.disconnected();
            server.disconnectClient(this);
            printText("Вы были отключены от сервера");
        }
    }
    //мы посылаем
    public void answer(String message){
        if(connected){
            if(!message.isEmpty()){
                server.message(getLogin() + ": " + message);
            }

        }
        else {
            printText("Вы не подключены к серверу. Для отправки сообщений произведите подключение.");
        }
    }
// нам посылают
    public void printText(String text){
        view.sendMessage(text);
    }
}
