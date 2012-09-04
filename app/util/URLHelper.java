package util;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * User: lbouin
 * Date: 21/08/12
 * Time: 01:02
 */
public class URLHelper {

    public static final String USERS_SELF_URL = "https://api.foursquare.com/v2/users/self?oauth_token=USER_TOKEN&client_id=CLT_ID&client_secret=CLT_SECRET&v=VERSION";
    public static final String REPLY_URL = "https://api.foursquare.com/v2/checkins/PUSH_ID/reply?text=REPLY_TEXT&oauth_token=USER_TOKEN&v=VERSION";
    public static final String STREET_EASY_URL = "http://streeteasy.com/nyc/api/areas/for_location?lon=LONGITUDE&lat=LATITUDE&key=STREET_EASY_KEY&format=json";

    // placeholders
    private static final String USER_TOKEN = "USER_TOKEN";
    private static final String VERSION = "VERSION";
    private static final String REPLY_TEXT = "REPLY_TEXT";
    private static final String PUSH_ID = "PUSH_ID";
    private static final String CLT_SECRET = "CLT_SECRET";
    private static final String CLT_ID = "CLT_ID";

    public static String buildUserSelfUrl(String userToken){

        String result = StringUtils.replace(USERS_SELF_URL, USER_TOKEN,userToken);
        result = StringUtils.replace(result, CLT_ID, FoursquareConfiguration.getInstance().getClientId());
        result = StringUtils.replace(result, CLT_SECRET, FoursquareConfiguration.getInstance().getClientSecret());
        result = StringUtils.replace(result, VERSION, FoursquareConfiguration.getInstance().getVersion());

        return result;
    }

    public static String buildReplyUrl(String pushedId, String text, String userToken){

        String result =  StringUtils.replace(REPLY_URL, USER_TOKEN,userToken);
        result = StringUtils.replace(result, PUSH_ID, ""+pushedId);
        result = StringUtils.replace(result, REPLY_TEXT, text);
        result = StringUtils.replace(result, VERSION, FoursquareConfiguration.getInstance().getVersion());

        return result;
    }

    public static String buildStreetEAsyUrl(String longitude, String latitude){

        String result =  StringUtils.replace(STREET_EASY_URL, "LONGITUDE",longitude);
        result = StringUtils.replace(result, "LATITUDE", latitude);
        result = StringUtils.replace(result,"STREET_EASY_KEY",StreetEasyConfiguration.getInstance().getKey());

        return result;
    }
}
