package ch.ny.livios.movielion.ui.moviedetails;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import ch.ny.livios.movielion.MovieDetailsActivity;
import ch.ny.livios.movielion.R;
import ch.ny.livios.movielion.model.MovieDetails;

public class MovieDetailsFragment extends Fragment {

    public static MovieDetailsFragment newInstance() {
        return new MovieDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.movie_details_fragment, container, false);

        TextView titleTextView = root.findViewById(R.id.textView_title_details);
        titleTextView.setText(MovieDetailsActivity.movieDetails.getTitle());

        TextView descriptionTextView = root.findViewById(R.id.textView_description_details);
        descriptionTextView.setText(MovieDetailsActivity.movieDetails.getDescription());

        ImageView posterImageView = root.findViewById(R.id.imageView_poster_details);
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + MovieDetailsActivity.movieDetails.getPicturePath()).into(posterImageView);

        TextView runtimeTextView = root.findViewById(R.id.textView_runtime_details);
        runtimeTextView.setText(minToString(MovieDetailsActivity.movieDetails.getRuntime()));

        TextView budgetTextView = root.findViewById(R.id.textView_budget_details);
        budgetTextView.setText(longToUSD(MovieDetailsActivity.movieDetails.getBudget()));

        TextView revenueTextView = root.findViewById(R.id.textView_revenue_details);
        revenueTextView.setText(longToUSD(MovieDetailsActivity.movieDetails.getRevenue()));

        TextView genresTextView = root.findViewById(R.id.textView_genre_details);
        genresTextView.setText(getGenresString());

        return root;
    }

    /**
     *
     * @param number
     * @return Long as String in USD Format
     */
    public static String longToUSD(Long number) {
        if (number == 0l) {
            return "No Info";
        }
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        return n.format(number * 100l / 100.0);
    }

    /**
     *
     * @return All the genres from the MovieDetails Object as String
     */
    public static String getGenresString() {
        String string = "";
        ArrayList<String> movie = MovieDetailsActivity.movieDetails.getGenres();
        for (int i=0; i<movie.size(); i++){
            if (i==0){
                string = movie.get(i);
            }else {
                string = string + ", " + movie.get(i);
            }
        }
        return string;
    }

    public static String minToString(int min) {
        if (min % 60l == 0) {
            return min / 60l + "h";
        } else if (min > 60l) {
            return min / 60l + "h " + min % 60l + "min";
        } else {
            return min + "min";
        }
    }
}
