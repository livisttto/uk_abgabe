package ch.ny.livios.movielion.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import ch.ny.livios.movielion.MovieDetailsActivity;
import ch.ny.livios.movielion.MovieLists;
import ch.ny.livios.movielion.R;

public class MovieListFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static MovieListFragment newInstance(int index) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.movie_list_fragment, container, false);
        ListView listView = root.findViewById(R.id.listview_main_movieList);
        listView.setAdapter(new MovieAdapter(this.getContext(), MovieLists.getCurrent(getArguments().getInt(ARG_SECTION_NUMBER))));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
                intent.putExtra("ID", MovieLists.getCurrent(getArguments().getInt(ARG_SECTION_NUMBER)).get(position).getId());
                startActivity(intent);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return root;
    }
}
