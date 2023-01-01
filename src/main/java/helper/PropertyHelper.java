package helper;

import core.Global;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHelper {

    public static Properties loadPropertiesByFilePath(String filePath) {
        Properties prop = new Properties();
        try (InputStream inputStream = new FileInputStream(filePath)) {
            prop.load(inputStream);
        } catch (IOException ex) {
            throw new RuntimeException("unable to find property file: " + filePath);
        }
        return prop;
    }

    public static String getEnvValue(String key) {
        return Global.get(Properties.class).getProperty(key);
    }
}