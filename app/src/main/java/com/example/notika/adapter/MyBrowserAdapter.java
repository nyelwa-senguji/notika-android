package com.example.notika.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notika.DetailActivity;
import com.example.notika.R;
import com.example.notika.model.Topic;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class MyBrowserAdapter extends FirestoreRecyclerAdapter<Topic, MyBrowserAdapter.BrowserViewHolder> {

    public MyBrowserAdapter(@NonNull FirestoreRecyclerOptions<Topic> options){
       super(options);
    }

    @NonNull
    @Override
    public BrowserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View browseItem = layoutInflater.inflate(R.layout.browse_list, parent, false);
        BrowserViewHolder browserViewHolder = new BrowserViewHolder(browseItem);
        return browserViewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull BrowserViewHolder browserViewHolder, int i, @NonNull Topic topic) {
        browserViewHolder.title_tv.setText(topic.getTopic_name());
        browserViewHolder.description_tv.setText(topic.getSubject());
        Picasso.get().load(topic.getImage()).into(browserViewHolder.horizontalImage);



    }

    class BrowserViewHolder extends RecyclerView.ViewHolder{

        private static final String TAG = "KEY";
        TextView title_tv;
        TextView description_tv;
        ImageView horizontalImage;
        LinearLayout mainLayout;

        BrowserViewHolder(@NonNull View itemView) {
            super(itemView);

            title_tv = itemView.findViewById(R.id.title_tv);
            description_tv = itemView.findViewById(R.id.description_tv);
            horizontalImage = itemView.findViewById(R.id.horizontalImage);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(itemView.getContext(), DetailActivity.class);
                    int pos = getAdapterPosition();
                    String id = getSnapshots().getSnapshot(pos).getId();
                    Toast.makeText(itemView.getContext(), "ID : " + id, Toast.LENGTH_LONG).show();
                    i.putExtra("ID", id);
                    i.putExtra("topic_name", title_tv.getText());
                    i.putExtra("subject", description_tv.getText());
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }
}
