package app.maqson.notika;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import app.maqson.notika.adapter.DetailAdapter;
import app.maqson.notika.model.Sub_Topic;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DetailActivity extends AppCompatActivity {

    Toolbar toolbar;

    String topic_name, topic_id;

    private DetailAdapter detailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolBar);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getData();
        setData();
    }

    private void getData(){
        if ( getIntent().hasExtra("topic_name") && getIntent().hasExtra("ID")){
            topic_name = getIntent().getStringExtra("topic_name");
            topic_id = getIntent().getStringExtra("ID");

            Query query = FirebaseFirestore.getInstance()
                    .collection("Sub_Topic")
                    .whereEqualTo("topic_id", topic_id);

            RecyclerView detailRecyclerView = (RecyclerView) findViewById(R.id.detailRecyclerView);

            FirestoreRecyclerOptions<Sub_Topic> options = new FirestoreRecyclerOptions.Builder<Sub_Topic>()
                    .setQuery(query, Sub_Topic.class)
                    .build();

            detailAdapter = new DetailAdapter(options);

            detailRecyclerView.setAdapter(detailAdapter);
            detailRecyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.VERTICAL, false));

            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(DetailActivity.this, DividerItemDecoration.VERTICAL);
            detailRecyclerView.addItemDecoration(itemDecoration);
        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        toolbar.setTitle(topic_name);
    }

    @Override
    public void onStart() {
        super.onStart();
        detailAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        detailAdapter.stopListening();
    }
}
