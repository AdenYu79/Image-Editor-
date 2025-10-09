import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class WeatherForecast {
    public static void main(String[] args) {
        double latitude  = 39.168804;
        double longitude = -86.536659;
        String unitFlag  = "F";

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--latitude":
                    latitude = Double.parseDouble(args[++i]);
                    break;
                case "--longitude":
                    longitude = Double.parseDouble(args[++i]);
                    break;
                case "--unit":
                    String u = args[++i].toUpperCase();
                    if ("C".equals(u) || "F".equals(u)) {
                        unitFlag = u;
                    } else {
                        System.err.println("Unknown unit: " + u + ", using F.");
                    }
                    break;
                default:
                    System.err.println("Unknown argument: " + args[i]);
            }
        }

        String tempUnitParam = unitFlag.equals("C") ? "celsius" : "fahrenheit";
        String degreeSym = unitFlag.equals("C") ? "°C" : "°F";
        String urlString = String.format(
                "https://api.open-meteo.com/v1/forecast?"
                        + "latitude=%f&longitude=%f"
                        + "&hourly=temperature_2m"
                        + "&temperature_unit=%s"
                        + "&timezone=EST",
                latitude, longitude, tempUnitParam
        );

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() != 200) {
                throw new IOException("HTTP GET failed: " + connection.getResponseCode());
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            connection.disconnect();

            JsonElement rootElem = JsonParser.parseString(sb.toString());
            JsonObject rootObj = rootElem.getAsJsonObject();
            JsonObject hourly = rootObj
                    .get("hourly")
                    .getAsJsonObject();
            JsonArray times = hourly
                    .get("time")
                    .getAsJsonArray();
            JsonArray temps = hourly
                    .get("temperature_2m")
                    .getAsJsonArray();

            System.out.printf("7-Day Forecast in %s:%n",
                    unitFlag.equals("C") ? "Celsius" : "Fahrenheit");

            for (int day = 0; day < 7; day++) {
                int dayStart = day * 24;
                String dateTime = times.get(dayStart).getAsString();
                String date = dateTime.split("T")[0];
                System.out.println("Forecast for " + date + ":");
                for (int h = 0; h < 24; h += 3) {
                    int idx = dayStart + h;
                    String time = times.get(idx).getAsString().split("T")[1];
                    double t = temps.get(idx).getAsDouble();
                    System.out.printf("  %s: %.1f%s%n", time, t, degreeSym);
                }
            }
        } catch (IOException e) {
            System.err.println("Error fetching weather: " + e.getMessage());
            e.printStackTrace();
        }
    }
}