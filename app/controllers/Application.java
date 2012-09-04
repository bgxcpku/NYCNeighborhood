package controllers;

import com.google.common.base.Preconditions;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.lbo.foursquare.Response;
import com.lbo.foursquare.model.Checkin;
import com.lbo.foursquare.response.SelfCompactResponse;
import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import models.ny.ConnectedUser;
import models.streeteasy.AreaByLocation;
import play.Logger;
import play.libs.WS;
import play.modules.guice.InjectSupport;
import play.mvc.*;
import services.FoursquareService;
import services.StreetEasyService;
import util.FoursquareConfiguration;
import util.URLHelper;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;

public class Application extends Controller {

    @Inject
    static StreetEasyService streetEasyService;

    @Inject
    static FoursquareService foursquareService;

    public static void index() {
        render();
    }

    public static void connected() {
        render();
    }

    public static void about() {
        render();
    }

    public static void changedMind() {
        render();
    }

    public static void policy() {
        render();
    }

    /**
     * First part of the foursquare authentification & authorization
     */
    public static void authenticate(){
        FoursquareApi foursquareApi = new FoursquareApi(FoursquareConfiguration.getInstance().getClientId(), FoursquareConfiguration.getInstance().getClientSecret(), FoursquareConfiguration.getInstance().getRedirectUrl());
        redirect(foursquareApi.getAuthenticationUrl());
    }

    public static void handleCallback(String code) {

        FoursquareApi foursquareApi = new FoursquareApi(FoursquareConfiguration.getInstance().getClientId(), FoursquareConfiguration.getInstance().getClientSecret(), FoursquareConfiguration.getInstance().getRedirectUrl());

        // Callback url contains authorization code
        try {

            foursquareApi.authenticateCode(code);
            String oauthToken = foursquareApi.getOAuthToken();

            String replyUrl = URLHelper.buildUserSelfUrl(oauthToken);

            //get the current user through an active user call to foursquare
            String replyAnswer = WS.url(replyUrl).get().getString();

            Type selfCompactUserResponseType = new TypeToken<Response<SelfCompactResponse>>() {}.getType();
            Response<SelfCompactResponse> foursquareResponse = new Gson().fromJson(replyAnswer, selfCompactUserResponseType);

            ConnectedUser user = ConnectedUser.findByUserId(foursquareResponse.response.user.id);

            if(user == null ){
                user = new ConnectedUser(foursquareResponse.response.user.id,user.token = oauthToken);
                user.save();
            }else{
                user.token = oauthToken;
                user.update();
            }

            connected();

        } catch (FoursquareApiException e) {

        }
    }


    public static void psh(String checkin, String secret){

        play.Logger.info("Just received a push from Foursquare");

        Checkin pushed = new Gson().fromJson(checkin, Checkin.class);

        play.Logger.info("Push user with id:'%s'",pushed.user.id);

        //this is to catch a test push for the foursquare developer website
        if(pushed.user.id == 1){
            play.Logger.info("It is a test push from foursquare");
            return;
        }

        AreaByLocation areaByLocation = streetEasyService.findAreaByLocation(pushed.venue.location.lat,pushed.venue.location.lng);

        play.Logger.info("Neigbhorhood: %s", areaByLocation.name);
        String reply = "You're in "+areaByLocation.name;

        ConnectedUser user = ConnectedUser.findByUserId(pushed.user.id);

        try{
            reply = URLEncoder.encode(reply, "UTF-8");
        }catch (UnsupportedEncodingException e){
            Logger.error(e,"Impossible to encode your text");
            reply = "We%20have%20no%20idea%20where%20you%20are...sorry";
        }

        foursquareService.callReply(pushed.id,user.token,reply);

        //String replyUrl = URLHelper.buildReplyUrl(pushed.id, reply, user.token);
        //String replyAnswer =  WS.url(replyUrl).post().getString();

    }

}