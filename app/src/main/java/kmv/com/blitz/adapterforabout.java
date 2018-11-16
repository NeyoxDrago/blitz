package kmv.com.blitz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nirnay Mittal on 09-09-2018.
 */

public class adapterforabout extends RecyclerView.Adapter<adapterforabout.Viewholder> {

    private List<String> urls = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private List<String> designation = new ArrayList<>();
    private Context context;

    public adapterforabout(List<String> urls, List<String> names, List<String> designation, Context context) {
        this.urls = urls;
        this.names = names;
        this.designation = designation;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.teamitem , parent , false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        RequestOptions options  = new RequestOptions().placeholder(R.drawable.ic_launcher_background);
        Glide.with(context).load(urls.get(position)).apply(options).into(holder.image);

        holder.name.setText(names.get(position));
        holder.designation.setText(designation.get(position));

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView name;
        TextView designation;

        public Viewholder(View itemView) {
            super(itemView);

            image= itemView.findViewById(R.id.memberImage);
            name  = itemView.findViewById(R.id.membername);
            designation = itemView.findViewById(R.id.memberdesignation);
        }
    }
}
