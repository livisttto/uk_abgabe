package ch.ny.livios.movielion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ch.ny.livios.movielion.model.MovieDetails;
import ch.ny.livios.movielion.API.TMDB;
import ch.ny.livios.movielion.ui.moviedetails.MovieDetailsFragment;

public class MovieDetailsActivity extends AppCompatActivity {

    public static MovieDetails movieDetails = new MovieDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);
        movieDetails = TMDB.getMovieDetails(getIntent().getIntExtra("ID", 1));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MovieDetailsFragment.newInstance())
                    .commitNow();
        }
    }
}
