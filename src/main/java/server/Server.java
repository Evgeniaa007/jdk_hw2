package server;

import client.Client;
import server.repository.FileOperations;
import server.repository.Repository;

import java.util.ArrayList;

public class Server {

    private boolean isServerWorking;
    private ArrayList<Client> clients; //для добавления пользователей и работы с ними
    private ServerView view;
    private Repository repository;

    public Server(ServerView view) {
        this.view = view;
        repository = new FileOperations();
        clients = new ArrayList<>();
        view.setServer(this);
    }

    public void printText(String text){
        view.sendMessage(text);
    }

    //метод, позволяющий отображать сообщения, а также сохранять их в файл
    public void message(String text){
        if (isServerWorking) {
            printText(text);
            totalDisplay(text);
            repository.saveLog(text);
        }
    }

    private void totalDisplay(String text){
        for (Client client: clients){
            client.printText(text);
        }
    }

    // при работе сервера добавляет клиента в список для дальнейшей работы
    public boolean loginClient(Client client){
        if(isServerWorking){
            clients.add(client);
            return true;
        }
        else{
            return false;
        }
    }

    public void disconnectClient(Client client){
        clients.remove(client);
        if (clients != null){
            client.disconnect();
        }
    }

    public void saveLog(String text){
        repository.saveLog(text);
    }

    // получение записей из файла
    public String getLog() {
        return repository.readLog();
    }

    // подключение сервера
    public void connect(){
        if(isServerWorking){
            printText("Сервер уже запущен.");
        }
        else{
            isServerWorking = true;
            printText("Сервер запущен!");
        }
    }

    // отключение сервера
    public void disconnect(){
        if(!isServerWorking) {
            printText("Работа сервера остановлена.");
        }
        else{
            isServerWorking = false;
            while(!clients.isEmpty()){
                disconnectClient(clients.get(clients.size()-1));
            }
        }
        printText("Сервер остановлен!");
    }
}
