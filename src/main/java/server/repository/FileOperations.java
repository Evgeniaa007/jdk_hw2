package server.repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations implements Repository{
    private static final String LOG_FILE = "src\\main\\java\\serverLog.txt";

    // запись в файл
    @Override
    public void saveLog(String text){
        try(FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(text);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // чтение из файла
    @Override
    public String readLog(){
        StringBuilder stringBuilder = new StringBuilder();
        try(FileReader reader = new FileReader(LOG_FILE)){
            int c;
            while((c=reader.read())!=-1){
                stringBuilder.append((char) c);
            }
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
