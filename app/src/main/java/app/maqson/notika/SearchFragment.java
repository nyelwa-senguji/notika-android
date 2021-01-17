package app.maqson.notika;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ferfalk.simplesearchview.SimpleSearchView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    SimpleSearchView searchView;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView startSearch = (TextView) view.findViewById(R.id.tv_search);
        startSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.showSearch();
            }
        });

        ImageView settings = (ImageView) view.findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SettingsActivity.class);
                startActivity(i);
            }
        });

        searchView = (SimpleSearchView) view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SimpleSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

            @Override
            public boolean onQueryTextCleared() {
                return false;
            }
        });
    }

}
