package kmv.com.blitz;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nirnay Mittal on 07-09-2018.
 */

public class dialogrecycleradapter extends RecyclerView.Adapter<dialogrecycleradapter.Viewholder>  {

    private Context context;
    private List<String> imagelist = new ArrayList<>();

    public dialogrecycleradapter(Context context, List<String> imagelist) {
        this.context = context;
        this.imagelist = imagelist;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.dialogitem , parent , false);
        return new Viewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int position) {

        final RequestOptions options = new RequestOptions().placeholder(R.drawable.ic_launcher_background);
        Glide.with(context).load(imagelist.get(position)).apply(options).into(holder.image);

        holder.image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Dialog a = new Dialog(context);
                a.setContentView(R.layout.gridgallerydialog);

                ImageView  image = a.findViewById(R.id.griddialogimage);
                Glide.with(context).load(imagelist.get(position)).apply(options).into(image);

                a.show();

                return true;
            }
        });



    }

    @Override
    public int getItemCount() {
        return imagelist.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {

        ImageView image;

        public Viewholder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.dialogitemimage);
        }
    }
}
