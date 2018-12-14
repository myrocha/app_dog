package br.com.appdog.constants;

public class Constants {

    public static final String SERVER_INSTANCE_NAME = "api-iddog.idwall.co";
    public static final int TIMEOUT = 60;
    public static String getBaseUrl() {
        return String.format("https://%s", SERVER_INSTANCE_NAME);
    }
}
