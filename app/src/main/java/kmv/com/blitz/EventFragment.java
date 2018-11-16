package kmv.com.blitz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventFragment extends Fragment
{

    private RecyclerView recycler;
    private List<String> imageurls = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private FirebaseAuth mauth;
    private DatabaseReference mdatabase;


    public EventFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event , container ,false);

        recycler = v.findViewById(R.id.Eventrecycler);
        mauth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("events");
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot i : dataSnapshot.getChildren())
                {
                    imageurls.add(i.child("image").getValue().toString());
                    names.add(i.child("name").getValue().toString());
                }

                eventrecycleradapter adapter = new eventrecycleradapter(getContext() , imageurls , names);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recycler.setLayoutManager(layoutManager);
                recycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recycler.setItemViewCacheSize(40);
        recycler.setHasFixedSize(true);
        recycler.setDrawingCacheEnabled(true);
        recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        return v;
    }


}