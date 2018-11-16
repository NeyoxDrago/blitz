package kmv.com.blitz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TechnologyYoutubeStepper extends AppCompatActivity {

    private ArrayList<String> video_id = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> contents = new ArrayList<>();

    private ViewPager viewPager;
    private View v;

    private DatabaseReference mdef;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_technology_youtube_stepper);

        viewPager = (ViewPager) findViewById(R.id.stepviewpager);
        mdef = FirebaseDatabase.getInstance().getReference();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();
        mdef.child("Technology Data").child("Artificial Intelligence").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot i : dataSnapshot.getChildren())
                {
                        video_id.add(i.child("video_id").getValue().toString());
                        titles.add(i.child("title").getValue().toString());
                        contents.add(i.child("content").getValue().toString());
                }

                video_id.add("dshbvhds");
                titles.add("dkjjdv");
                contents.add("jdvcuiynjhuhncd");

                video_id.add("dshbvhds");
                titles.add("dkjjdv");
                contents.add("jdvcuiynjhuhncd");


                adapterfoyoutubestepper adapter = new adapterfoyoutubestepper(TechnologyYoutubeStepper.this ,
                        video_id,titles ,contents);
                viewPager.setAdapter(adapter);
                    dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
