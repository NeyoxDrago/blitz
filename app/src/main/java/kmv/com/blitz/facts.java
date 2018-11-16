package kmv.com.blitz;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class facts extends AppCompatActivity {

    private RecyclerView recycler;
    private List<String> Facts = new ArrayList<>();
    private DatabaseReference mdef;
    private ProgressDialog progres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recycler = (RecyclerView) findViewById(R.id.recyclerviewforfacts);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        progres = new ProgressDialog(this);
        progres.setMessage("Fetching facts");
        progres.show();

        mdef = FirebaseDatabase.getInstance().getReference();
        mdef.child("facts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Facts.clear();
                for(DataSnapshot i : dataSnapshot.getChildren()) {
                    if (!Facts.contains(i.getValue().toString())) {
                        Facts.add(i.getValue().toString());
                    }
                }


                adapterforfacts adapter = new adapterforfacts(Facts , facts.this );
                recycler.setAdapter(adapter);
                progres.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recycler.setHasFixedSize(true);
        recycler.setItemViewCacheSize(40);
        recycler.buildDrawingCache(true);


    }

}
