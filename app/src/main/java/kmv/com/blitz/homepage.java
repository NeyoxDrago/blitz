package kmv.com.blitz;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class homepage extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four, R.drawable.five};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private RecyclerView recycler;
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private TextView thought;
    private FirebaseAuth mauth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mauth= FirebaseAuth.getInstance();

        init();
        recycler = (RecyclerView) findViewById(R.id.homerecycler);


        thought = (TextView) findViewById(R.id.thought);
        thought.setText("Fight for your Dreams.. ");
        //----------------------------Grid data-----------------------------------------------------------

        img.add("https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.clker.com%2Fcliparts%2FM%2Fo%2FW%2Fd%2FC%2Fj%2Fabout-icon-hi.png&f=1");
        names.add("About");

        img.add("http://downloadicons.net/sites/default/files/small-house-with-a-chimney-icon-70053.png");
        names.add("Home");

        img.add("http://rotaryglobaleagles.org/wp-content/uploads/2015/09/articles.png");
        names.add("Articels");

        img.add("https://cdn2.iconfinder.com/data/icons/seo-internet-marketing-5/256/Events_Calendar-512.png");
        names.add("Events");

        img.add("http://blog.firelightfoundation.org/wp-content/uploads/2013/12/FACT_1.png");
        names.add("Facts");

        img.add("http://niat.com.bn/wp-content/uploads/2014/09/GALLERY_ICON.png");
        names.add("Gallery");

        img.add("http://missionpossibleapp.com/wp-content/uploads/2015/09/TeamIcon.png");
        names.add("Blitz Society");

        img.add("http://www.microbase.com.ph/images/sce/products/CPPS%20icon.png");
        names.add("technology");

        img.add("https://ca.hudson.com/portals/us/images/icons/icon-idea.png");
        names.add("Ideas");

        img.add("skdvhkjdsvkjd");
        names.add("others");

        //------------------------------------------------------------------------------------------------

        GridLayoutManager layout = new GridLayoutManager(this , 2);
        adapterforhomepage adapter = new adapterforhomepage(img , names , homepage.this);

                recycler.setLayoutManager(layout);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);

    }
    private void init() {

        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]) ;

        mPager =  findViewById(R.id.pager);

        mPager.setAdapter(new sliderAdapter(ImagesArray , homepage.this));


        CircleIndicator indicator = (CircleIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                else
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        if(mauth.getCurrentUser() == null)
        {
            Intent a = new Intent(homepage.this , SignInPage.class);
            startActivity(a);
        }
    }
}
