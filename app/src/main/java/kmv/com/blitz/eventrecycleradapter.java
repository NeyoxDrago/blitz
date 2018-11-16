package kmv.com.blitz;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class eventrecycleradapter extends RecyclerView.Adapter<eventrecycleradapter.ViewHolder> {

    private List<String> mimageurls = new ArrayList<>();
    private List<String> eventnames = new ArrayList<>();
    private Context context;
    private RecyclerView recycler;
    private FirebaseAuth mauth;
    private  ArrayList<String> array = new ArrayList<>();;
    private StorageReference mdatabase;

    public eventrecycleradapter(Context context ,List<String> mimageurls, List<String> eventnames) {
        this.mimageurls = mimageurls;
        this.eventnames = eventnames;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventitem , parent , false);
       return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_foreground);

        Glide.with(context).load(mimageurls.get(position)).apply(requestOptions).into(holder.image);
        holder.name.setText(eventnames.get(position));

        final Dialog a = new Dialog(context);
        a.setContentView(R.layout.dialog);

        mauth = FirebaseAuth.getInstance();
        mdatabase = FirebaseStorage.getInstance().getReference().child("events");

        recycler = a.findViewById(R.id.dialogrecyclerview);

        StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(2 , LinearLayout.VERTICAL);
        recycler.setLayoutManager(layout);

        final StorageReference storage = mdatabase.child(eventnames.get(position));

        final boolean[] s = {true};
        for(int i = 1; i < 5;i++) {
            if(!s[0])
            {
               break;
            }
            try {
                storage.child(i+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        if (!array.contains(uri.toString())) {
                            array.add(uri.toString());
                        }
                        s[0] = false;


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(context, "Error while retrieving !!!!!!!!!", Toast.LENGTH_SHORT).show();
                    }
                });

            }catch(Exception e)
            {
                break;
            }
        }

        dialogrecycleradapter adapter = new dialogrecycleradapter(context , array);
        recycler.setAdapter(adapter);
        recycler.setItemViewCacheSize(40);
        recycler.setHasFixedSize(true);
        recycler.setDrawingCacheEnabled(true);
        recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                a.show();
            }


        });
    }


    @Override
    public int getItemCount() {
        return eventnames.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.eventimage);
            name = itemView.findViewById(R.id.eventname);

        }
    }

}
