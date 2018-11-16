package kmv.com.blitz;

import android.app.ApplicationErrorReport;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView articles;
    private ArrayList<String> articleimageUrls = new ArrayList<>();
    private ArrayList<String> articleusername = new ArrayList<>();
    private ArrayList<String> articletext = new ArrayList<>();
    private DatabaseReference mdef;

    private ProgressDialog dialog;

    private FirebaseAuth mauth;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SwipeRefreshLayout swipe = (SwipeRefreshLayout) findViewById(R.id.swiper);

        mauth = FirebaseAuth.getInstance();

        add = (Button) findViewById(R.id.addarticle);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this , AddActivity.class);
                startActivity(a);
            }
        });

        articles = (RecyclerView) findViewById(R.id.articles);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Retrieving data");
        dialog.setCancelable(false);
        dialog.show();
/*
            articleimageUrls.add("http://bdfjade.com/data/out/102/6066356-wallpaper-image.jpeg");
            articletext.add("lsdknvdsnvlkds");
            articleusername.add("dkuhvsdj");

            articleimageUrls.add("https://lokeshdhakar.com/projects/lightbox2/images/image-3.jpg");
            articletext.add("lsdknvdsnvlkds");
            articleusername.add("dkuhvsdj");

                articleimageUrls.add("https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg?resize=640%2C426");
            articletext.add("lsdknvdsnvlkds");
            articleusername.add("dkuhvsdj");

            articleimageUrls.add("http://www.miadumont.com/wp-content/uploads/2011/06/chatons-Linda.jpg");
            articletext.add("lsdknvdsnvlkds");
            articleusername.add("dkuhvsdj");

            articleimageUrls.add("http://dubeat.com/wp-content/uploads/Image-1.jpeg");
            articletext.add("lsdknvdsnvlkds");
            articleusername.add("dkuhvsdj");

            articleimageUrls.add("https://pluspng.com/img-png/horse-transparent-png-image-1450.png");
            articletext.add("lsdknvdsnvlkds");
            articleusername.add("dkuhvsdj");

            articleimageUrls.add("https://i.pinimg.com/736x/dc/7b/62/dc7b62949f201b74145b3509bb396f5b.jpg");
            articletext.add("lsdknvdsnvlkds");
            articleusername.add("dkuhvsdj");

            //----------------------------------------------------------------------------------------------------


            articleimageUrls.add("http://bdfjade.com/data/out/102/6066356-wallpaper-image.jpeg");
            articletext.add("lsdknvdsnvlkds");
            articleusername.add("dkuhvsdj");

            articleimageUrls.add("https://lokeshdhakar.com/projects/lightbox2/images/image-3.jpg");
            articletext.add("lsdknvdsnvlkds");
            articleusername.add("dkuhvsdj");

            articleimageUrls.add("https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg?resize=640%2C426");
            articletext.add("lsdknvdsnvlkds");
            articleusername.add("dkuhvsdj");

            articleimageUrls.add("http://www.miadumont.com/wp-content/uploads/2011/06/chatons-Linda.jpg");
            articletext.add("lsdknvdsnvlkds");
            articleusername.add("dkuhvsdj");

            articleimageUrls.add("http://dubeat.com/wp-content/uploads/Image-1.jpeg");
            articletext.add("lsdknvdsnvlkds");
            articleusername.add("dkuhvsdj");

            articleimageUrls.add("https://pluspng.com/img-png/horse-transparent-png-image-1450.png");
            articletext.add("lsdknvdsnvlkds");
            articleusername.add("dkuhvsdj");

            articleimageUrls.add("https://i.pinimg.com/736x/dc/7b/62/dc7b62949f201b74145b3509bb396f5b.jpg");
            articletext.add("lsdknvdsnvlkds");
            articleusername.add("dkuhvsdj");
        }
        */

        final StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(2 , LinearLayout.VERTICAL);

        mdef = FirebaseDatabase.getInstance().getReference().child("articles");


        mdef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mdef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Toast.makeText(MainActivity.this, dataSnapshot.getChildrenCount() +"", Toast.LENGTH_SHORT).show();
                for(DataSnapshot i: dataSnapshot.getChildren()) {

                    String url = i.child("imageurl").getValue().toString();
                    String name = i.child("username").getValue().toString();
                    String text = i.child("text").getValue().toString();


                    articleimageUrls.add(url);
                    articleusername.add(name);
                    articletext.add(text);
                }

                dialog.dismiss();
                staggeredrecyclerviewadapter adapter = new staggeredrecyclerviewadapter(MainActivity.this ,articleusername,articleimageUrls , articletext);
                adapter.setHasStableIds(true);
                adapter.notifyDataSetChanged();

                articles.setLayoutManager(layout);
                articles.setAdapter(adapter);
                articles.setHasFixedSize(true);
                articles.setItemViewCacheSize(20);
                articles.setDrawingCacheEnabled(true);
                articles.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                articles.refreshDrawableState();
                swipe.setRefreshing(false);
                swipe.refreshDrawableState();

            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1 , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.signout:
                mauth.signOut();
                Intent a= new Intent(MainActivity.this , SignInPage.class);
                startActivity(a);
                break;

            case R.id.language:
                Intent d = new Intent(MainActivity.this , languages.class);
                startActivity(d);
                break;

            case R.id.gallery:
                Intent h = new Intent(MainActivity.this , GlleryPage.class);
                startActivity(h);

        }
    return  true;
    }


}
