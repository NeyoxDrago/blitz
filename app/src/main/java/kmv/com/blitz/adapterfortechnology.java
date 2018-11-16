package kmv.com.blitz;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mingle.entity.MenuEntity;
import com.mingle.sweetpick.BlurEffect;
import com.mingle.sweetpick.DimEffect;
import com.mingle.sweetpick.RecyclerViewDelegate;
import com.mingle.sweetpick.SweetSheet;
import com.mingle.sweetpick.ViewPagerDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nirnay Mittal on 09-09-2018.
 */

public class adapterfortechnology extends RecyclerView.Adapter<adapterfortechnology.ViewHolder>{

    private List<String> text = new ArrayList<>();
    private List<String> image  = new ArrayList<>();
    private Context context;
    private SweetSheet msweetsheet;
    private RelativeLayout rl;

    public adapterfortechnology(List<String> text, List<String> image, Context context , RelativeLayout rl ) {
        this.text = text;
        this.image = image;
        this.rl = rl;
        this.msweetsheet = new SweetSheet(this.rl);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.technologyitem ,parent , false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        RequestOptions options  = new RequestOptions().placeholder(R.drawable.ic_launcher_foreground);

        Glide.with(context).load(image.get(position)).apply(options).into(holder.image);

        final ArrayList<MenuEntity> list  = new ArrayList<>();
        final DatabaseReference mdef = FirebaseDatabase.getInstance().getReference();
        final int i = position;
        holder.textView.setText(text.get(position));
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msweetsheet.setMenuList(R.menu.menu3);
                msweetsheet.setDelegate(new RecyclerViewDelegate(true));

                msweetsheet.setBackgroundEffect(new DimEffect(0.25f));

                msweetsheet.show();
                Toast.makeText(context, "ellooo", Toast.LENGTH_SHORT).show();

                msweetsheet.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener() {
                    @Override
                    public boolean onItemClick(int position, MenuEntity menuEntity) {

                        msweetsheet.dismiss();
                        Intent a  = new Intent(context ,data.class);
                        a.putExtra("Field" , text.get(i) );
                        a.putExtra("option" , menuEntity.title);
                        context.startActivity(a);

                        return true;
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return text.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.technoimage);
            textView = itemView.findViewById(R.id.technotext);
        }
    }
}
