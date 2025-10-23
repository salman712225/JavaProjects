import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WeatherApp extends Application {

    private TextArea outputArea;
    private TextField cityField;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Weather Forecast App");

        Label cityLabel = new Label("Enter City:");
        cityField = new TextField();
        Button getWeatherBtn = new Button("Get Weather");
        outputArea = new TextArea();
        outputArea.setEditable(false);

        VBox vbox = new VBox(10, cityLabel, cityField, getWeatherBtn, outputArea);
        vbox.setPadding(new Insets(15));

        getWeatherBtn.setOnAction(e -> fetchWeather());

        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
        stage.show();

        // Load last searched city if available
        loadLastCity();
    }

    private void fetchWeather() {
        String city = cityField.getText().trim();
        if (city.isEmpty()) {
            outputArea.setText("Please enter a city name.");
            return;
        }

        try {
            String data = WeatherService.getWeatherData(city);
            String result = WeatherService.parseWeather(data);
            outputArea.setText(result);
            saveLastCity(city);
        } catch (Exception ex) {
            outputArea.setText("Error fetching data. Please check the city name or internet connection.");
        }
    }

    private void saveLastCity(String city) {
        try (FileWriter writer = new FileWriter("data/lastCity.txt")) {
            writer.write(city);
        } catch (IOException e) {
            System.out.println("Could not save last city.");
        }
    }

    private void loadLastCity() {
        try {
            String lastCity = new String(Files.readAllBytes(Paths.get("data/lastCity.txt")));
            cityField.setText(lastCity);
        } catch (IOException e) {
            // ignore if file doesn't exist
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
