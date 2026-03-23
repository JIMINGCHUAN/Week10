package com.example.week9;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JsonUtils {

    public static ArrayList<Movie> loadMoviesFromJson(Context context) throws Exception {
        ArrayList<Movie> movieList = new ArrayList<>();

        try {
            String jsonString = readJsonFromAssets(context, "movies.json");
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject movieObject = jsonArray.getJSONObject(i);

                    String title = parseTitle(movieObject);
                    int year = parseYear(movieObject);
                    String genre = parseGenre(movieObject);
                    String poster = parsePoster(movieObject);

                    Movie movie = new Movie(title, year, genre, poster);
                    movieList.add(movie);

                } catch (Exception e) {
                    // Skip one bad movie object, continue loading the rest
                    movieList.add(new Movie(
                            "Unknown Title",
                            -1,
                            "Unknown Genre",
                            "default_poster"
                    ));
                }
            }

        } catch (IOException e) {
            throw new Exception("Could not find movies.json file.");
        } catch (org.json.JSONException e) {
            throw new Exception("Invalid JSON format.");
        } catch (Exception e) {
            throw new Exception("Error loading movie data: " + e.getMessage());
        }

        return movieList;
    }

    private static String readJsonFromAssets(Context context, String fileName) throws IOException {
        InputStream inputStream = context.getAssets().open(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        reader.close();
        inputStream.close();
        return builder.toString();
    }

    private static String parseTitle(JSONObject movieObject) {
        if (!movieObject.has("title") || movieObject.isNull("title")) {
            return "Unknown Title";
        }

        String title = movieObject.optString("title", "").trim();
        if (title.isEmpty()) {
            return "Unknown Title";
        }

        return title;
    }

    private static int parseYear(JSONObject movieObject) {
        if (!movieObject.has("year") || movieObject.isNull("year")) {
            return -1;
        }

        Object yearObject = movieObject.opt("year");

        if (yearObject instanceof Integer) {
            int year = (Integer) yearObject;
            return year > 0 ? year : -1;
        }

        if (yearObject instanceof Long) {
            long year = (Long) yearObject;
            return year > 0 ? (int) year : -1;
        }

        if (yearObject instanceof Double) {
            double year = (Double) yearObject;
            if (year > 0 && year == Math.floor(year)) {
                return (int) year;
            } else {
                return -1;
            }
        }

        if (yearObject instanceof String) {
            try {
                int year = Integer.parseInt(((String) yearObject).trim());
                return year > 0 ? year : -1;
            } catch (NumberFormatException e) {
                return -1;
            }
        }

        return -1;
    }

    private static String parseGenre(JSONObject movieObject) {
        if (!movieObject.has("genre") || movieObject.isNull("genre")) {
            return "Unknown Genre";
        }

        String genre = movieObject.optString("genre", "").trim();
        if (genre.isEmpty()) {
            return "Unknown Genre";
        }

        return genre;
    }

    private static String parsePoster(JSONObject movieObject) {
        if (!movieObject.has("poster") || movieObject.isNull("poster")) {
            return "default_poster";
        }

        String poster = movieObject.optString("poster", "").trim();
        if (poster.isEmpty()) {
            return "default_poster";
        }

        return poster;
    }
}