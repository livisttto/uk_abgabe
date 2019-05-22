package ch.ny.livios.movielion.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.Objects;

import ch.ny.livios.movielion.MovieDetailsActivity;
import ch.ny.livios.movielion.MovieLists;
import ch.ny.livios.movielion.R;
import ch.ny.livios.movielion.API.TMDB;

public class SearchFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private ListView listView;

    public static SearchFragment newInstance(int index) {
        SearchFragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.search_fragment, container, false);
        SearchView searchView = root.findViewById(R.id.searchview_main_search);
        listView = root.findViewById(R.id.listview_main_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
                //Pass the data
                intent.putExtra("ID", Objects.requireNonNull(MovieLists.getCurrent(Objects.requireNonNull(getArguments()).getInt(ARG_SECTION_NUMBER))).get(position).getId());
                startActivity(intent);
            }
        });
        return root;
    }

    private void callSearch(String query) {
        MovieLists.setSearch(TMDB.getSearch(query, 20));
        listView.setAdapter(new MovieAdapter(this.getContext(), MovieLists.getCurrent(Objects.requireNonNull(getArguments()).getInt(ARG_SECTION_NUMBER))));
    }
}
