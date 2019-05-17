package ch.ny.livios.movielion;

import java.util.List;

import ch.ny.livios.movielion.model.Movie;

public class MovieLists {
    private static List<Movie> search;
    private static List<Movie> popular;
    private static List<Movie> toprated;
    private static List<Movie> upcoming;

    public static void setAll(List<List<Movie>> list) {
        MovieLists.popular = list.get(1);
        MovieLists.toprated = list.get(2);
        MovieLists.upcoming = list.get(3);
    }

    public static List<Movie> getCurrent(int position) {
        switch (position) {
            case 1:
                return search;
            case 2:
                return popular;
            case 3:
                return toprated;
            case 4:
                return upcoming;
            default:
                return null;
        }
    }

    public static List<Movie> getSearch() {
        return search;
    }

    public static void setSearch(List<Movie> search) {
        MovieLists.search = search;
    }

    public static List<Movie> getPopular() {
        return popular;
    }

    public static void setPopular(List<Movie> popular) {
        MovieLists.popular = popular;
    }

    public static List<Movie> getToprated() {
        return toprated;
    }

    public static void setToprated(List<Movie> toprated) {
        MovieLists.toprated = toprated;
    }

    public static List<Movie> getUpcoming() {
        return upcoming;
    }

    public static void setUpcoming(List<Movie> upcoming) {
        MovieLists.upcoming = upcoming;
    }
}
