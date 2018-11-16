package kmv.com.blitz;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private DatabaseReference mdef;
    private List<String> question =new ArrayList<>();
    private List<String> option1 = new ArrayList<>();
    private List<String> option2 = new ArrayList<>();
    private List<String> option3 = new ArrayList<>();
    private List<String> option4 = new ArrayList<>();
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        viewPager = (ViewPager) findViewById(R.id.testviewpager);

        mdef = FirebaseDatabase.getInstance().getReference();
        progress = new ProgressDialog(this);
        progress.setMessage("Loading");
        progress.show();
        mdef.child("Test").child("Python").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot i : dataSnapshot.getChildren())
                {
                    option1.add(i.child("option1").getValue().toString());
                    option2.add(i.child("option2").getValue().toString());
                    option3.add(i.child("option3").getValue().toString());
                    option4.add(i.child("option4").getValue().toString());
                    question.add(i.child("text").getValue().toString());

                }

                adapterfortestviewpager adapter = new adapterfortestviewpager(TestActivity.this,question ,option1,option2,option3,option4);
                viewPager.setAdapter(adapter);


                progress.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                progress.dismiss();
                Toast.makeText(TestActivity.this, "Error Occurred !!!!!", Toast.LENGTH_SHORT).show();
            }
        });

        viewPager.setOffscreenPageLimit(2);



    }
}
