package kmv.com.blitz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdeaActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private List<String> idea = new ArrayList<>();
    private DatabaseReference mdef;
    private FirebaseAuth mauth;
    private ProgressDialog progressDialog , progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mauth = FirebaseAuth.getInstance();
        mdef = FirebaseDatabase.getInstance().getReference();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Ideas");
        progressDialog.show();

        progress = new ProgressDialog(IdeaActivity.this);
        progress.setMessage("Wait a second");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog a= new Dialog(IdeaActivity.this);
                a.setContentView(R.layout.ideadialog);
                a.show();

                final EditText IDEA = a.findViewById(R.id.creativeidea);
                Button b = a.findViewById(R.id.submitidea);

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        progress.show();
                        if(!TextUtils.isEmpty(IDEA.getText().toString()))
                        {
                            String id = IDEA.getText().toString().trim();

                            Map<String, String> map = new HashMap<>();
                            map.put("ides" , id);
                            map.put("time" , String.valueOf(new Timestamp(System.currentTimeMillis()/1000)));

                            mdef.child("Ideas").child("all ideas").push().setValue(id);
                            mdef.child("Ideas").child(mauth.getCurrentUser().getUid()).push().setValue(map);

                            progress.dismiss();
                        }
                        else
                            {
                                progress.dismiss();
                                Toast.makeText(IdeaActivity.this, "Please share a idea ....", Toast.LENGTH_SHORT).show();
                            }

                    }
                });


            }
        });

        recycler =(RecyclerView) findViewById(R.id.idearecycler);

        recycler.setLayoutManager(new LinearLayoutManager(IdeaActivity.this));


        mdef.child("Ideas").child("all ideas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot i: dataSnapshot.getChildren())
                {
                    if(!idea.contains(i.getValue().toString()))
                    idea.add(i.getValue().toString());
                }
                progressDialog.dismiss();
                adapterforidea adapter = new adapterforidea(idea , IdeaActivity.this);
                recycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }

}
