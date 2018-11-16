package kmv.com.blitz;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private List<String> names = new ArrayList<>();
    private List<String> designation = new ArrayList<>();
    private List<String> urls =new ArrayList<>();
    private DatabaseReference mdef;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        recycler = (RecyclerView) findViewById(R.id.aboutrecyclerview);

        mdef = FirebaseDatabase.getInstance().getReference();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(AboutActivity.this, 3);
        recycler.setLayoutManager(gridLayoutManager);

        dialog =new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();

        mdef.child("Members").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot i : dataSnapshot.getChildren())
                {
                    urls.add(i.child("image").getValue().toString());
                    designation.add(i.child("designation").getValue().toString());
                    names.add(i.child("name").getValue().toString());
                }
                dialog.dismiss();
                adapterforabout adapter = new adapterforabout(urls , names ,designation ,AboutActivity.this);
                recycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recycler.setItemViewCacheSize(40);
        recycler.setHasFixedSize(true);
        recycler.setDrawingCacheEnabled(true);
        recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }
}
