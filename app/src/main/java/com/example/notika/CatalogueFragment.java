package com.example.notika;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class CatalogueFragment extends Fragment {

    String s1[];

    int images[] = {R.drawable.arts, R.drawable.design, R.drawable.development,
                    R.drawable.finance, R.drawable.math, R.drawable.physics, R.drawable.science,
                    R.drawable.computing, R.drawable.networking,R.drawable.it};

    RecyclerView recyclerView;

    RecyclerView.ItemDecoration itemDecoration;

    public CatalogueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catalogue, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.topics);

        MyAdapter myAdapter =  new MyAdapter(getActivity(), s1, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }


}