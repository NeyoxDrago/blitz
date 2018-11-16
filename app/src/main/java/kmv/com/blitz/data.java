package kmv.com.blitz;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class data extends YouTubeBaseActivity    {

    private RecyclerView mrecycler;
    private List<String> content = new ArrayList<>();
    private List<String> urls = new ArrayList<>();
    private TextView mtext;
    private DatabaseReference mdef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        String a = getIntent().getStringExtra("Field");
        final String b = getIntent().getStringExtra("option");

        Toast.makeText(this, a+ " " +b , Toast.LENGTH_SHORT).show();

        mdef = FirebaseDatabase.getInstance().getReference();

        mrecycler = (RecyclerView) findViewById(R.id.datarecycler);
        mtext = (TextView) findViewById(R.id.titletext);


        mdef.child("Technology Data").child(a).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot i: dataSnapshot.getChildren())
                {
                    if(i.child("title").getValue().toString().equals(b))
                    {
                    if(!urls.contains(i.child("video_id").getValue().toString())
                            && !content.contains(i.child("content").getValue().toString()))
                    {
                        content.add(i.child("content").getValue().toString());
                        urls.add(i.child("video_id").getValue().toString());
                    }

                }

                }
                adapterfordata adapter = new adapterfordata(content ,urls , data.this);
                mrecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mrecycler.setLayoutManager(new LinearLayoutManager(this));
        mrecycler.setHasFixedSize(true);

    }
}
