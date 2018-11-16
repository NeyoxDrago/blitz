package kmv.com.blitz;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Nirnay Mittal on 06-09-2018.
 */

public class gridgalleryadapter extends RecyclerView.Adapter<gridgalleryadapter.gridviewholder>
{
    private ArrayList<String> imageurls = new ArrayList<>();
    private Context context ;
    private RecyclerView recycler;



    public gridgalleryadapter( Context context , ArrayList<String> imageurls) {
        this.imageurls = imageurls;
        this.context = context;
    }

    @NonNull
    @Override
    public gridviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.griditem , parent ,false);
        return new gridviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull gridviewholder holder, final int position) {

        final RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_foreground);

        Glide.with(context).load(imageurls.get(position)).apply(requestOptions).into(holder.image);

        holder.image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Dialog a = new Dialog(context);
                a.setContentView(R.layout.gridgallerydialog);

                ImageView image = a.findViewById(R.id.griddialogimage);

                Glide.with(context).load(imageurls.get(position)).apply(requestOptions).into(image);

                a.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageurls.size();
    }

    class gridviewholder extends RecyclerView.ViewHolder {

        ImageView image;
        public gridviewholder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.gridimage);
        }
    }
}
