package kmv.com.blitz;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nirnay Mittal on 10-09-2018.
 */

public class adapterforstepper extends PagerAdapter {

    private ArrayList<String> titles;
    private ArrayList<String> content;
    private LayoutInflater inflater;
    private Context context;

    public adapterforstepper(ArrayList<String> titles, ArrayList<String> content , Context context) {
        this.titles = titles;
        this.content = content;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = inflater.inflate(R.layout.stepperitemlayout ,container ,false);

        assert v!=null;
        View back = v.findViewById(R.id.stepperitemview);
        TextView Title = v.findViewById(R.id.steppertitle);
        TextView Content  =v .findViewById(R.id.steppercontent);

        Random a = new Random();
        back.setBackgroundColor(Color.argb(255 , a.nextInt(255) ,a.nextInt(255),a.nextInt(255) ));
        Title.setText(titles.get(position));
        Content.setText(content.get(position));
        Content.setMovementMethod(new ScrollingMovementMethod());

        container.addView(v ,0);
        return v;
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
