package kmv.com.blitz;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class techologystepper extends AppCompatActivity {

    private ViewPager viewpager;
    private int currentPage;
    private CircleIndicator indicator;
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> contents = new ArrayList<>();

    private DatabaseReference mdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_techologystepper);

        String techno = getIntent().getStringExtra("technology");

        viewpager =(ViewPager) findViewById(R.id.stpperviewpager);
        indicator = (CircleIndicator) findViewById(R.id.stepperindicator);

        indicator.setViewPager(viewpager);
        mdatabase = FirebaseDatabase.getInstance().getReference();

        mdatabase.child("Technology Data").child(techno).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot i : dataSnapshot.getChildren()) {
                    if (!titles.contains(i.child("title").getValue().toString()) && !contents.contains(i.child("content").getValue().toString())) {
                        titles.add(i.child("title").getValue().toString());
                        contents.add(i.child("content").getValue().toString());
                    }
                }
                adapterforstepper adapter = new adapterforstepper(titles,contents , techologystepper.this);
                viewpager.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        final int NUM_PAGES = titles.size();
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                else
                    viewpager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);


        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPage = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
