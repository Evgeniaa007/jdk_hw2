import client.Client;
import client.ClientGUI;
import server.Server;
import server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(new ServerWindow());
        new Client(new ClientGUI(server), server);
        new Client(new ClientGUI(server), server);
    }
}