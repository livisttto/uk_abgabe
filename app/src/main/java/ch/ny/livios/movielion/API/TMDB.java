package ch.ny.livios.movielion.API;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ch.ny.livios.movielion.model.Movie;
import ch.ny.livios.movielion.model.MovieDetails;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

public class TMDB {
    public static JSONObject jsonobj;

    public static JSONObject getGSONObject(String url) {
        jsonobj = null;
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.e("failure Response", mMessage);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonString = response.body().string();
                try {
                    jsonobj = new JSONObject(jsonString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            while (jsonobj == null) {
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonobj;
    }

    public static ArrayList<Movie> getMovieList(String url, int lenght) {
        ArrayList<Movie> movieList = new ArrayList<>();
        try {
            JSONObject jsonObject = getGSONObject(url);
            JSONArray jarr = jsonObject.getJSONArray("results");
            for (int i = 0; i < lenght; i++) {
                Movie movie = new Movie();
                movie.setId(jarr.getJSONObject(i).getInt("id"));
                movie.setTitle(jarr.getJSONObject(i).getString("title"));
                movie.setPicturePath(jarr.getJSONObject(i).getString("poster_path"));
                movie.setDescription(jarr.getJSONObject(i).getString("overview"));
                movieList.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    public static ArrayList<Movie> getSearch(String query, int lenght) {
        return getMovieList("https://api.themoviedb.org/3/search/movie?api_key=4e1597118e7d25bf7cefc1d1565cf71a&query=" + query, lenght);
    }

    public static MovieDetails getMovieDetails(int id) {
        MovieDetails movieDetails = new MovieDetails();

        try {
            JSONObject jsonObject = getGSONObject("https://api.themoviedb.org/3/movie/" + id + "?api_key=4e1597118e7d25bf7cefc1d1565cf71a");
            //Fill Object
            movieDetails.setId(jsonObject.getInt("id"));
            movieDetails.setTitle(jsonObject.getString("original_title"));
            movieDetails.setPicturePath(jsonObject.getString("poster_path"));
            movieDetails.setDescription(jsonObject.getString("overview"));

            ArrayList<String> genres = new ArrayList<>();
            for (int i = 0; i < jsonObject.getJSONArray("genres").length(); i++) {
                genres.add(jsonObject.getJSONArray("genres").getJSONObject(i).getString("name"));
            }
            movieDetails.setGenres(genres);
            movieDetails.setRuntime(jsonObject.getInt("runtime"));
            movieDetails.setBudget(jsonObject.getLong("budget"));
            movieDetails.setRevenue(jsonObject.getLong("revenue"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieDetails;
    }
}
