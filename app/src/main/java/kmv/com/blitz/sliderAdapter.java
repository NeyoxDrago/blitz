package kmv.com.blitz;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Nirnay Mittal on 08-09-2018.
 */

public class sliderAdapter extends PagerAdapter {

    private ArrayList<Integer> Images;
    private LayoutInflater inflater;
    private Context context;

    public sliderAdapter(ArrayList<Integer> images, Context context) {
        Images = images;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Images.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = inflater.inflate(R.layout.fragment_sliderfragment , container ,false);

        assert v!=null;
        ImageView image = v.findViewById(R.id.sliderfragmentimage);

        image.setImageResource(Images.get(position));

        container.addView(v ,0);

        return v;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {

    }

    @Nullable
    @Override
    public Parcelable saveState() {
        return null;
    }
}
