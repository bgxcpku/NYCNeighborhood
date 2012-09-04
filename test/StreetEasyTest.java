import models.streeteasy.AreaByLocation;
import org.junit.Test;
import play.test.FunctionalTest;
import services.StreetEasyService;
import services.StreetEasyServicePlayImp;

public class StreetEasyTest extends FunctionalTest {

    @Test
    public void testThatIndexPageWorks() {
        StreetEasyService service = new StreetEasyServicePlayImp();

        AreaByLocation result = service.findAreaByLocation("40.72809214560253","-73.99112284183502");
        assertEquals("East Village",result.name);

    }

}