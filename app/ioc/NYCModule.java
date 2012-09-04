package ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import controllers.Application;
import services.FoursquareService;
import services.FoursquareServicePlayImp;
import services.StreetEasyService;
import services.StreetEasyServicePlayImp;

/**
 * User: lbouin
 * Date: 04/09/12
 * Time: 12:52
 */
public class NYCModule extends AbstractModule {

    public NYCModule(){super();}

    @Override
    protected void configure() {
        requestStaticInjection(Application.class);

        play.Logger.info("Guice.configure");

        bind(StreetEasyService.class).to(StreetEasyServicePlayImp.class).in(Singleton.class);
        bind(FoursquareService.class).to(FoursquareServicePlayImp.class).in(Singleton.class);
    }
}
