package server;

import client.ClientGUI;
import server.repository.Repository;

import java.util.ArrayList;

public class Server {

    private boolean isServerWorking;
    private ArrayList<ClientGUI> clients; //для добавления пользователей и работы с ними
    private ServerView view;
    private Repository repository;

    public Server(ServerView view) {
        this.view = view;
    }

    //метод, позволяющий отображать сообщения, а также сохранять их в файл
    public void message(String text){
        if (isServerWorking) {
            text += "";
            appendLog(text);
            totalDisplay(text);
            saveLog(text);
        }

    }

    private void totalDisplay(String text){
        for (ClientGUI client: clients){
            client.appendLog(text);
        }
    }

    public void  disconnectClient(ClientGUI clientGUI){
        clients.remove(clientGUI);
        if (clients != null){
            clientGUI.disconnectFromServer();
        }

    }


    // получение записей из файла
    public String getLog() {
        return readLog();
    }
    // при работе сервера добавляет клиента в список для дальнейшей работы
    public boolean loginClient(ClientGUI client){
        if(isServerWorking){
            clients.add(client);
            return true;
        }
        else{
            return false;
        }
    }


    //Метод добавления текста в окне сервера
    public void appendLog(String text){
        log.append(text + "\n");
    }



}
