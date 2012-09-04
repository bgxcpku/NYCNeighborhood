package util;

import play.Play;

/**
 * User: lbouin
 * Date: 22/08/12
 * Time: 21:36
 */
public class StreetEasyConfiguration {
    private static StreetEasyConfiguration ourInstance = new StreetEasyConfiguration();

    private String key;

    public static StreetEasyConfiguration getInstance() {
        return ourInstance;
    }

    private StreetEasyConfiguration() {
        key = Play.configuration.getProperty("streeteasy.key");
    }

    public String getKey(){
        return this.key;
    }
}
