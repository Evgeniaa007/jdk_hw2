package client;

public interface ClientView {

    // отображение сообщения в графическом интерфейсе
    void sendMessage(String message);

    //метод, который используется для визуализации отключения
    // А именно возвращет окно клиента в исходное состояние
    void disconnected();

    void setClientController(Client client);
}
