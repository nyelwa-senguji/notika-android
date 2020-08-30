package com.example.notika;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrowseFragment extends Fragment {

    String s1[];
    String s2[];

    int images[] = {R.drawable.diff, R.drawable.complex, R.drawable.law,
            R.drawable.electromagnetic, R.drawable.trig};

    RecyclerView horizontalRecyclerView, horizontalRecyclerView2, horizontalRecyclerView3;

    public BrowseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_browse, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        horizontalRecyclerView = (RecyclerView) view.findViewById(R.id.horizontalRecyclerView);
        horizontalRecyclerView2 = (RecyclerView) view.findViewById(R.id.horizontalRecyclerView2);
        horizontalRecyclerView3 = (RecyclerView) view.findViewById(R.id.horizontalRecyclerView3);

        s1 = getResources().getStringArray(R.array.topic_title);
        s2 = getResources().getStringArray(R.array.topic_description);

        MyBrowserAdapter myBrowserAdapter = new MyBrowserAdapter(getActivity(), s1, s2, images);
        horizontalRecyclerView.setAdapter(myBrowserAdapter);
        horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        horizontalRecyclerView2.setAdapter(myBrowserAdapter);
        horizontalRecyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        horizontalRecyclerView3.setAdapter(myBrowserAdapter);
        horizontalRecyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        //horizontal overScroll decoration
        OverScrollDecoratorHelper.setUpOverScroll(horizontalRecyclerView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
        OverScrollDecoratorHelper.setUpOverScroll(horizontalRecyclerView2, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
        OverScrollDecoratorHelper.setUpOverScroll(horizontalRecyclerView3, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
    }
}
