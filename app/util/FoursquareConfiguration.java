package util;

import play.Play;

/**
 * User: lbouin
 * Date: 20/08/12
 * Time: 00:21
 */
public class FoursquareConfiguration {

    private static FoursquareConfiguration ourInstance = new FoursquareConfiguration();

    private String clientId;
    private String clientSecret;
    private String redirectUrl;
    private String version;

    public static FoursquareConfiguration getInstance() {
        return ourInstance;
    }

    private FoursquareConfiguration() {
        clientId = Play.configuration.getProperty("4sqre.client.id");
        clientSecret = Play.configuration.getProperty("4sqre.client.secret");
        redirectUrl = Play.configuration.getProperty("4sqre.redirectURL");
        version = Play.configuration.getProperty("4sqre.version");
    }

    public String getClientId(){
        return this.clientId;
    }

    public String getClientSecret(){
        return this.clientSecret;
    }

    public String getRedirectUrl(){
        return this.redirectUrl;
    }

    public String getVersion(){
        return this.version;
    }

}
