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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Nirnay Mittal on 01-09-2018.
 */

public class staggeredrecyclerviewadapter extends RecyclerView.Adapter<staggeredrecyclerviewadapter.staggeredViewHolder>{

    private ArrayList<String> usernamelist = new ArrayList<>();
    private ArrayList<String> imageurls= new ArrayList<>();
    private ArrayList<String> articletextlist = new ArrayList<>();
    private Context c;

    public staggeredrecyclerviewadapter(Context context , ArrayList<String> musername, ArrayList<String> imageurls, ArrayList<String> articletext) {

        this.c = context;
        this.usernamelist = musername;
        this.imageurls = imageurls;
        this.articletextlist = articletext;
    }

    @NonNull
    @Override
    public staggeredViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.article , parent , false );
        return new staggeredViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull staggeredViewHolder holder, final int position) {


        final RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background);

        Glide.with(c)
                .load(imageurls.get(position))
                .apply(requestOptions)
                .into(holder.articleImage);

        String te = "";
                if(articletextlist.get(position).length() >= 15) {
                    for (int i = 0; i < 15; i++) {
                        te = te + articletextlist.get(position).charAt(i);
                    }

                }
                else
                {
                    for (int i = 0; i < articletextlist.get(position).length(); i++) {
                        te = te + articletextlist.get(position).charAt(i);
                    }
                }
        holder.articletext.setText(te + ".....");
        holder.username.setText(usernamelist.get(position));


        holder.articleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog a =new Dialog(c);
                a.setContentView(R.layout.item);
                a.setCancelable(true);
                a.show();

                ImageView img = a.findViewById(R.id.dialogimage);
                TextView user = a.findViewById(R.id.dialogarticleusernmae);
                TextView t = a.findViewById(R.id.dialogtext);

                Glide.with(a.getContext()).load(imageurls.get(position)).apply(requestOptions).into(img);
                user.setText(usernamelist.get(position));
                t.setText(articletextlist.get(position));


            }
        });

    }

    @Override
    public int getItemCount() {
        return imageurls.size();
    }

    class staggeredViewHolder extends RecyclerView.ViewHolder
    {
        ImageView articleImage;
        TextView username;
        TextView articletext;


        public staggeredViewHolder(View itemView) {
            super(itemView);

            articleImage = (ImageView) itemView.findViewById(R.id.articleimage);
            username = (TextView) itemView.findViewById(R.id.articleusername);
            articletext = (TextView) itemView.findViewById(R.id.articletext);
        }
    }
}
