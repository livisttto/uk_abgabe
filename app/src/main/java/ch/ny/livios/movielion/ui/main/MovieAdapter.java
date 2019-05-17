package ch.ny.livios.movielion.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

import ch.ny.livios.movielion.model.Movie;
import ch.ny.livios.movielion.R;

public class MovieAdapter extends ArrayAdapter<Movie> {

    private LayoutInflater mLayoutInflater;

    public MovieAdapter(Context context, List<Movie> items) {
        super(context, R.layout.movie_item);
        addAll(items);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.movie_item, null);

            viewHolder = new ViewHolder();
            viewHolder.posterImageView = convertView.findViewById(R.id.imageView_poster_listview);
            viewHolder.titleTextView = convertView.findViewById(R.id.textview_title_listview);
            viewHolder.descriptionTextView = convertView.findViewById(R.id.textview_description_listview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + getItem(position).getPicturePath()).placeholder(R.drawable.loading).error(R.drawable.no_image).into(viewHolder.posterImageView);

        String title = getItem(position).getTitle();
        viewHolder.titleTextView.setText(title);

        String description = getItem(position).getDescription();
        viewHolder.descriptionTextView.setText(description);

        return convertView;
    }

    public static class ViewHolder {
        ImageView posterImageView;
        TextView titleTextView;
        TextView descriptionTextView;
    }

}
