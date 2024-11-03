package server.repository;

public interface Repository {
    void saveLog(String text);

    String readLog();

}
