package kmv.com.blitz;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Nirnay Mittal on 12-09-2018.
 */

public class adapterfoyoutubestepper extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<String> video_id = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> contents = new ArrayList<>();
    private AppCompatActivity app = new AppCompatActivity();

    public adapterfoyoutubestepper(Context context, ArrayList<String> video_id, ArrayList<String> titles, ArrayList<String> content) {
        this.context = context;
        this.video_id = video_id;
        this.inflater = LayoutInflater.from(context);
        this.titles = titles;
        this.contents = content;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return video_id.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        View v = inflater.inflate(R.layout.youtubestep , container ,false);
        assert v != null;

        YouTubePlayerSupportFragment youtube = (YouTubePlayerSupportFragment) app.getSupportFragmentManager().findFragmentById(R.id.youtubefragment);
        TextView title = v.findViewById(R.id.titleofpage);
        TextView content = v.findViewById(R.id.content);

        content.setText("jsndvjknd");
        title.setText("kdjbjksvjb");

        try {
            youtube.initialize(youtubeconfig.API_KEY, new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                    youTubePlayer.setFullscreen(false);
                    youTubePlayer.setShowFullscreenButton(false);

                    if (!b) {
                        youTubePlayer.cueVideo(video_id.get(position));
                    }
                }


                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            });

            container.addView(v, 0);
            return v;
        }catch(Exception e){
            Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

        return true;
    }

        @Override
        public void restoreState (@Nullable Parcelable state, @Nullable ClassLoader loader){

        }

        @Nullable
        @Override
        public Parcelable saveState () {
            return null;
        }
    }

