package utils;

import database.DataBase;

import java.io.*;

/**
 * Created by Влад on 18.12.2016.
 */
public class DBSaver {

    public static DataBase load() {

        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream("DB.ser"))) {
            return (DataBase) stream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            return new DataBase();
        }
    }

    public static void save(DataBase dataBase) {

        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("DB.ser"))){
            stream.writeObject(dataBase);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
