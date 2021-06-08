package core;

import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    private static Properties properties;
    private static String filename = "config.properties";

    static {
        properties = new Properties();
        try{
            properties.load(ReadProperties.class.getClassLoader().getResourceAsStream(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAPIUrl() {
        return properties.getProperty("api_url");
    }

    public static String getAuthToken() {
        return properties.getProperty("token");
    }

    public static String getUsername() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }
}
