package ch.ny.livios.movielion;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ch.ny.livios.movielion.model.Movie;
import ch.ny.livios.movielion.API.TMDB;
import ch.ny.livios.movielion.ui.main.MovieListFragment;
import ch.ny.livios.movielion.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private static final int[] TAB_URLS = new int[]{R.string.tab_url_1, R.string.tab_url_2, R.string.tab_url_3, R.string.tab_url_4};
    private static boolean internet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            Resources res = this.getResources();
            List<List<Movie>> movieListsList = new ArrayList<List<Movie>>() {
            };
            for (int i = 0; i < 4; i++) {
                ArrayList<Movie> movieList = TMDB.getMovieList(res.getString(R.string.api_url) + res.getString(TAB_URLS[i]) + "?api_key=" + res.getString(R.string.api_key), 50);
                Log.w("URL", res.getString(R.string.api_url) + res.getString(TAB_URLS[i]) + "?api_key=" + res.getString(R.string.api_key));
                movieListsList.add(movieList);
            }
            MovieLists.setAll(movieListsList);

            setContentView(R.layout.activity_main_online);
            SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
            ViewPager viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(sectionsPagerAdapter);
            TabLayout tabs = findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);
        } else {
            setContentView(R.layout.activity_main_offline);
            MovieListFragment.newInstance(1);
        }
    }
}