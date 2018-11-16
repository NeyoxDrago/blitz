package kmv.com.blitz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment
{

    private AsymmetricGridView list;
    private RecyclerView recycler;
    private DatabaseReference mstorage;

    public GalleryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery , container, false);

        recycler = (RecyclerView) v.findViewById(R.id.recylerview);

        final ArrayList<String> array  = new ArrayList<>();

        mstorage = FirebaseDatabase.getInstance().getReference().child("gallery");

        mstorage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot i: dataSnapshot.getChildren())
                {
                    if(!array.contains(i.getValue().toString()))
                       {
                           array.add(i.getValue().toString());
                       }
                }
                gridgalleryadapter adpater = new gridgalleryadapter(getContext(),array);
                adpater.notifyDataSetChanged();
                recycler.setLayoutManager(new StaggeredGridLayoutManager(2 , LinearLayout.VERTICAL));
                recycler.setHasFixedSize(true);
                recycler.setItemViewCacheSize(20);
                recycler.setDrawingCacheEnabled(true);
                recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                recycler.setAdapter(adpater);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return v;
    }
}