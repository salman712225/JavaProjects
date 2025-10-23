import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherService {
    private static final String API_KEY = "0b80352949146f987319488675ac8769"; // Replace with your OpenWeatherMap API key

    public static String getWeatherData(String city) throws Exception {
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" 
                            + city + "&appid=" + API_KEY + "&units=metric";

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }

    public static String parseWeather(String jsonResponse) {
        JSONObject obj = new JSONObject(jsonResponse);
        JSONObject main = obj.getJSONObject("main");
        JSONObject wind = obj.getJSONObject("wind");
        JSONObject weather = obj.getJSONArray("weather").getJSONObject(0);

        double temp = main.getDouble("temp");
        int humidity = main.getInt("humidity");
        double windSpeed = wind.getDouble("speed");
        String condition = weather.getString("description");

        return String.format("Temperature: %.1f°C\nHumidity: %d%%\nWind Speed: %.1f m/s\nCondition: %s",
                             temp, humidity, windSpeed, condition);
    }
}
