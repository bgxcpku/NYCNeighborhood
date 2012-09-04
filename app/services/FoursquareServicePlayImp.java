package services;

import play.libs.WS;
import util.URLHelper;

public class FoursquareServicePlayImp implements FoursquareService{

    @Override
    public void callReply(String checkinId, String userToken, String replyText) {

        play.Logger.info("Calling foursquare reply endPoind: checkinId:%s,userToken:%s,reply:%s",checkinId,userToken,replyText);

        String replyUrl = URLHelper.buildReplyUrl(checkinId, replyText, userToken);
        String replyAnswer =  WS.url(replyUrl).post().getString();

    }
}
