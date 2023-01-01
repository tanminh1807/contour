package _base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

public class BasePDO implements Cloneable, Serializable {

    public String toJsonString() {
        return new GsonBuilder().setLenient().create().toJson(this);
    }

    public static <T>T getObjectFromJsonString(String jsonString, Class<T> object){
        return new Gson().fromJson(jsonString,object);
    }
}
