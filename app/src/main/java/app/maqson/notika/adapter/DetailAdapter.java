package app.maqson.notika.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.maqson.notika.OpenPdfActivity;
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

    class DetailViewHolder extends RecyclerView.ViewHolder{

        TextView sub_topic;

        DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            sub_topic = itemView.findViewById(R.id.sub_topic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(itemView.getContext(), OpenPdfActivity.class);
                    int pos = getAdapterPosition();
                    String id = getSnapshots().getSnapshot(pos).getId();
                    Toast.makeText(itemView.getContext(), "ID : " + id, Toast.LENGTH_LONG).show();
                    i.putExtra("ID", id);
                    i.putExtra("sub_topic_name", sub_topic.getText());
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }
}
