package services;

import models.streeteasy.AreaByLocation;

public interface StreetEasyService {

    public AreaByLocation findAreaByLocation(String latitude, String longitude);

}
