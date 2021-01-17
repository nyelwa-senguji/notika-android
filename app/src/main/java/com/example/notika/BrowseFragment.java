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

import com.example.notika.adapter.MyBrowserAdapter;
import com.example.notika.model.Topic;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrowseFragment extends Fragment {

    private MyBrowserAdapter myBrowserAdapterOne, myBrowserAdapterTwo;

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

        Query queryOne = FirebaseFirestore.getInstance()
                .collection("Topic")
                .limit(4);

        Query queryTwo = FirebaseFirestore.getInstance()
                .collection("Topic")
                .whereEqualTo("subject", "Physics");

        RecyclerView horizontalRecyclerView = (RecyclerView) view.findViewById(R.id.horizontalRecyclerView);
        RecyclerView horizontalRecyclerView2 = (RecyclerView) view.findViewById(R.id.horizontalRecyclerView2);
        RecyclerView horizontalRecyclerView3 = (RecyclerView) view.findViewById(R.id.horizontalRecyclerView3);

        FirestoreRecyclerOptions<Topic> optionsOne = new FirestoreRecyclerOptions.Builder<Topic>()
                .setQuery(queryOne, Topic.class)
                .build();

        FirestoreRecyclerOptions<Topic> optionsTwo = new FirestoreRecyclerOptions.Builder<Topic>()
                .setQuery(queryTwo, Topic.class)
                .build();


        myBrowserAdapterOne = new MyBrowserAdapter(optionsOne);
        myBrowserAdapterTwo = new MyBrowserAdapter(optionsTwo);

        horizontalRecyclerView.setAdapter(myBrowserAdapterOne);
        horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        horizontalRecyclerView2.setAdapter(myBrowserAdapterTwo);
        horizontalRecyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        horizontalRecyclerView3.setAdapter(myBrowserAdapterOne);
        horizontalRecyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        //horizontal overScroll decoration
        OverScrollDecoratorHelper.setUpOverScroll(horizontalRecyclerView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
        OverScrollDecoratorHelper.setUpOverScroll(horizontalRecyclerView2, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
        OverScrollDecoratorHelper.setUpOverScroll(horizontalRecyclerView3, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
    }

    @Override
    public void onStart() {
        super.onStart();
        myBrowserAdapterOne.startListening();
        myBrowserAdapterTwo.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        myBrowserAdapterOne.stopListening();
        myBrowserAdapterTwo.stopListening();
    }
}
