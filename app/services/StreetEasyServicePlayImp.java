package services;

import com.google.gson.Gson;
import models.streeteasy.AreaByLocation;
import play.libs.WS;
import util.URLHelper;

public class StreetEasyServicePlayImp implements StreetEasyService{

    @Override
    public AreaByLocation findAreaByLocation(String latitude, String longitude) {
        play.Logger.info("findAreaByLocation(%s,%s)",latitude,longitude);

        String url = URLHelper.buildStreetEAsyUrl(longitude, latitude);
        String streetEasyResponse = WS.url(url).get().getString();
        AreaByLocation areaByLocation = new Gson().fromJson(streetEasyResponse, AreaByLocation.class);

        return areaByLocation;
    }

}
