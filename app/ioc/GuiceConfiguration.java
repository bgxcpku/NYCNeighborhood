package ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import controllers.Application;
import play.modules.guice.GuiceSupport;
import services.FoursquareService;
import services.FoursquareServicePlayImp;
import services.StreetEasyService;
import services.StreetEasyServicePlayImp;

public class GuiceConfiguration extends GuiceSupport {

    @Override
    protected Injector configure() {
        return Guice.createInjector(new AbstractModule() {


            @Override
            protected void configure() {

                requestStaticInjection(Application.class);

                play.Logger.info("Guice.configure");

                bind(StreetEasyService.class).to(StreetEasyServicePlayImp.class).in(Singleton.class);
                bind(FoursquareService.class).to(FoursquareServicePlayImp.class).in(Singleton.class);

            }
        });
    }
}
