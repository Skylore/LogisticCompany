package utils;

import com.google.gson.Gson;

/**
 * Created by serhii on 12/18/16.
 */
public class Converter {

    // get
    private static Gson gson = new Gson();

    public static String toJson(Object object){
        return gson.toJson(object);
    }

    public static<T> T fromJson(String json, Class<T> type){
        return gson.fromJson(json, type);
    }

}
