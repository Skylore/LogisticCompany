package database;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EmptyStackException;
import java.util.stream.Collectors;

public class Logger {

    public void write(String json) {

        try {
            PrintWriter printWriter = new PrintWriter("dataBaseLog.txt");
            printWriter.write(json);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read() {

        try {
            return Files.readAllLines(Paths.get("dataBaseLog.txt")).stream().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            try {
                FileWriter writer = new FileWriter(new File("dataBaseLog.txt"));
                writer.write("{\"requests\":[],\"delivered\":[],\"workRequests\":[],\"supportRequests\":[]}");
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        throw new EmptyStackException();
    }
}
