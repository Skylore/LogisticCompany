import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import geolocation.controller.Location;
import org.junit.Assert;
import org.junit.Test;

public class TestGeolacation {

    @Test
    public void test(){
        GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();

        Location location = googleMapsAPI.findLocation("Україна", "Київ", "Ніжинська", "29");
        Location location1 = googleMapsAPI.findLocation("Україна", "Київ", "Чколівський бульвар", "39");

        Assert.assertEquals(1980.0, googleMapsAPI.getDistance(location, location1), 1.0);
    }

}
