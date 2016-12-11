package geolocation.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.*;
import geolocation.model.LocationSearchResult;
import geolocation.model.Result;
import geolocation.model.WayPoints;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Scanner;

public class GoogleMapsAPIImpl implements GoogleMapsAPI {

    private Gson gson = new Gson();

    public static final String GET_ADDRESS_FORMATTED_QUERY_TEMPLATE =
            "https://maps.googleapis.com/maps/api/geocode/json?address=%s,%s,%s&key=%s";

    public static final String GET_ADDRESS_UNFORMATTED_QUERY_TEMPLATE =
            "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s";

    public static final String GET_DISTANCE_QUERY_TEMPLATE =
            "https://maps.googleapis.com/maps/api/directions/json?origin=place_id:%s&destination=place_id:%s&key=%s";

    public static final String GOOGLE_API_KEY =
            "AIzaSyAw1tCKgkKOkkCaun42Em3kCf0R9Oo7tZY";

    @Override
    public Location findLocation(String unformatted) {
        final String preparedQuery = String.format(GET_ADDRESS_UNFORMATTED_QUERY_TEMPLATE, prepareQueryReplaceSpaces(unformatted), GOOGLE_API_KEY);

        try {
            String jsonResponse = sendGetRequest(preparedQuery);
            // TODO replace using marshalling, dont convert
            LocationSearchResult locationSearchResult = gson.fromJson(jsonResponse, LocationSearchResult.class);
            Result result = locationSearchResult.getResults().get(0);
            geolocation.model.Location googleLocation = result.getGeometry().getLocation();

            return new Location(result.getFormattedAddress(), googleLocation.getLat(),googleLocation.getLng(), result.getPlaceId());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String prepareQueryReplaceSpaces(String unformatted) {
        return unformatted.replaceAll(" ", "+");
    }

    @Override//todo finish all task
    public Location findLocation(String country, String city, String street, String houseNum) {
        final String preparedQuery = String.format(GET_ADDRESS_FORMATTED_QUERY_TEMPLATE, prepareQueryReplaceSpaces((houseNum + " " + street)), city, country, GOOGLE_API_KEY);

        try {

            String jsonResponse = sendGetRequest(preparedQuery);
            LocationSearchResult locationSearchResult = gson.fromJson(jsonResponse, LocationSearchResult.class);

            Result result = locationSearchResult.getResults().get(0);
            geolocation.model.Location googleLocation = result.getGeometry().getLocation();

            return new Location(result.getFormattedAddress(), googleLocation.getLat(),googleLocation.getLng(), result.getPlaceId());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Location convert(String jsonResponse) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject root = (JSONObject) jsonParser.parse(jsonResponse);

        JSONArray result = (JSONArray) root.get("results");
        JSONObject firstFound = (JSONObject) result.get(0);


        JSONObject geometry = (JSONObject) firstFound.get("geometry");
        String placeId = firstFound.get("place_id").toString();

        JSONObject location = (JSONObject) geometry.get("location");

        String addressFormattedName = firstFound.get("formatted_address").toString();
        String lat = location.get("lat").toString();
        String lng = location.get("lng").toString();

        return new Location(addressFormattedName,
                Double.parseDouble(lat), Double.parseDouble(lng), placeId);
    }

    private String sendGetRequest(String preparedQuery) throws IOException {
        URL obj = new URL(preparedQuery);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        return getStringContent(con.getInputStream());


    }

    private String getStringContent(InputStream is) throws IOException {
        Scanner sc = new Scanner(is);
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine()).append("\n");
        }

        return sb.toString();
    }

    @Override
    public double getDistance(Location pointA, Location pointB) {
        final String formattedQuery = String.format(GET_DISTANCE_QUERY_TEMPLATE,
                pointA.getPlaceId(), pointB.getPlaceId(), GOOGLE_API_KEY);

        try {
            //todo use gsoup library for better mapping
            String body = sendGetRequest(formattedQuery);

            WayPoints wayPoints = gson.fromJson(body, WayPoints.class);

            JsonParser jsonParser = new JsonParser();
            JsonElement element = jsonParser.parse(body);

            JsonObject asJsonObject = element.getAsJsonObject();
            JsonArray routes = asJsonObject.getAsJsonArray("routes");
            JsonObject asJsonObject1 = routes.get(0).getAsJsonObject();
            JsonArray legs = asJsonObject1.getAsJsonArray("legs");
            JsonObject asJsonObject2 = legs.get(0).getAsJsonObject();
            JsonPrimitive jsonObject = asJsonObject2.getAsJsonObject("distance").getAsJsonPrimitive("value");


            return wayPoints.getRoutes().get(0).getLegs().get(0).getDistance().getValue();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
