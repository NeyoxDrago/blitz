package kmv.com.blitz;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class adapterforhomepage extends RecyclerView.Adapter<adapterforhomepage.Viewholder> implements View.OnClickListener {

    private List<String> images = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private Context context;

    public adapterforhomepage(List<String> images, List<String> names, Context context) {
        this.images = images;
        this.names = names;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.homeitem , parent , false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {

        RequestOptions options = new RequestOptions().placeholder(R.drawable.ic_launcher_background);
        Glide.with(context).load(images.get(position)).apply(options).into(holder.image);

        holder.text.setText(names.get(position));

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(position)
                {
                    case 0:
                        Toast.makeText(context, "Selected About" , Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(context, "Selected Home" , Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Intent a = new Intent(context , MainActivity.class);
                        context.startActivity(a);
                        break;
                    case 3:
                        Intent b = new Intent(context , languages.class);
                        context.startActivity(b);
                        break;
                    case 4:
                        Intent e = new Intent(context , facts.class);
                        context.startActivity(e);
                        break;
                    case 5:
                        Intent c = new Intent(context , GlleryPage.class);
                        context.startActivity(c);
                        break;
                    case 6:
                        Intent m = new Intent(context , AboutActivity.class);
                        context.startActivity(m);
                        break;
                    case 7:
                        Intent l = new Intent(context , TechnologyActivity.class);
                        context.startActivity(l);
                        break;
                    case 8:
                        Intent k = new Intent(context , IdeaActivity.class);
                        context.startActivity(k);
                        break;
                    case 9:
                        Intent p = new Intent(context,SpriteActivity.class);
                        context.startActivity(p);
                        break;
                    default:
                        Toast.makeText(context, "checked  " + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    @Override
    public void onClick(View v) {
    }

    class Viewholder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        public Viewholder(View itemView) {
            super(itemView);

         image = itemView.findViewById(R.id.homerecyclerimage);
         text = itemView.findViewById(R.id.homepagerecyclertext);

        }
    }
}
