package kmv.com.blitz;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mingle.entity.MenuEntity;
import com.mingle.sweetpick.BlurEffect;
import com.mingle.sweetpick.SweetSheet;
import com.mingle.sweetpick.ViewPagerDelegate;
import com.roger.match.library.MatchTextView;

import java.util.ArrayList;
import java.util.List;

public class TechnologyActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private List<String> text = new ArrayList<>();
    private List<String> image  = new ArrayList<>();
    private DatabaseReference mdef;
    private ProgressDialog progress;
    private SweetSheet msweetsheet;
    private RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);

        recycler = findViewById(R.id.technologyrecycler);

        rl = (RelativeLayout) findViewById(R.id.rl);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        mdef = FirebaseDatabase.getInstance().getReference();

        progress = new ProgressDialog(this);
        progress.setMessage("Please Wait");
        progress.show();
        mdef.child("Technology").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot i: dataSnapshot.getChildren()) {

                    if (!image.contains(i.child("imageurl").getValue().toString()) && !text.contains(i.child("name").getValue().toString())) {
                        image.add(i.child("imageurl").getValue().toString());
                        text.add(i.child("name").getValue().toString());
                    }
                }

                adapterfortechnology adapter = new adapterfortechnology(text , image , TechnologyActivity.this ,rl);
                recycler.setAdapter(adapter);
                progress.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recycler.setHasFixedSize(true);
        recycler.setItemViewCacheSize(20);
        recycler.setDrawingCacheEnabled(true);
        recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

    }

    public void Setup()
    {
        msweetsheet = new SweetSheet(rl);
        msweetsheet.setMenuList(R.menu.menu3);
        msweetsheet.setDelegate(new ViewPagerDelegate());
        msweetsheet.setBackgroundEffect(new BlurEffect(0.5f));
        msweetsheet.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener() {
            @Override
            public boolean onItemClick(int position, MenuEntity menuEntity) {
                Toast.makeText(TechnologyActivity.this, menuEntity.title + " Selected" , Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

}
