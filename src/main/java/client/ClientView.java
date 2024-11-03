package client;

public interface ClientView {
    // отображение сообщения в графическом интерфейсе
    void sendMessage(String message);

    //отключение клиента, производимое со стороны сервера
    void disconnectFromServer();
}
