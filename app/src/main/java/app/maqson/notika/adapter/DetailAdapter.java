package app.maqson.notika.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.maqson.notika.R;
import app.maqson.notika.model.Sub_Topic;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class DetailAdapter extends FirestoreRecyclerAdapter<Sub_Topic, DetailAdapter.DetailViewHolder> {

    public DetailAdapter(@NonNull FirestoreRecyclerOptions<Sub_Topic> options){
        super(options);
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View browseItem = layoutInflater.inflate(R.layout.detail_list, parent, false);
        DetailViewHolder detailViewHolder = new DetailViewHolder(browseItem);
        return detailViewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull DetailViewHolder detailViewHolder, int i, @NonNull Sub_Topic sub_topic) {
        detailViewHolder.sub_topic.setText(sub_topic.getSub_topic_name());


    }

    static class DetailViewHolder extends RecyclerView.ViewHolder{


        TextView sub_topic;

        DetailViewHolder(@NonNull View itemView) {
            super(itemView);

            sub_topic = itemView.findViewById(R.id.sub_topic);

        }
    }
}
