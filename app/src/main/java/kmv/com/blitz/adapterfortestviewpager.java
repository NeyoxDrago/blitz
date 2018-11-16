package kmv.com.blitz;

import android.content.Context;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class adapterfortestviewpager extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<String> question =new ArrayList<>();
    private List<String> option1 = new ArrayList<>();
    private List<String> option2 = new ArrayList<>();
    private List<String> option3 = new ArrayList<>();
    private List<String> option4 = new ArrayList<>();


    public adapterfortestviewpager(Context context, List<String> question, List<String> option1, List<String> option2, List<String> option3, List<String> option4) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    @Override
    public int getCount() {
        return question.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
    }

    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {
        super.restoreState(state, loader);
    }

    @Nullable
    @Override
    public Parcelable saveState() {
        return super.saveState();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final View v = inflater.inflate(R.layout.testitem, container ,false);
        assert v!=null;

        TextView questionNum = v.findViewById(R.id.testtext);
        final RadioGroup group = v.findViewById(R.id.testradiogroup);
        RadioButton option1 = v.findViewById(R.id.option1);
        RadioButton option2 = v.findViewById(R.id.option2);
        RadioButton option3 = v.findViewById(R.id.option3);
        RadioButton option4 = v.findViewById(R.id.option4);

        final Button m = v.findViewById(R.id.next);

        Toast.makeText(context, ""+"Question "+(position +1), Toast.LENGTH_SHORT).show();

        questionNum.setText(question.get(position));
        option1.setText(this.option1.get(position));
        option2.setText(this.option2.get(position));
        option3.setText(this.option3.get(position));
        option4.setText(this.option4.get(position));

        final String[] n = {"",""};
        final DatabaseReference mdef = FirebaseDatabase.getInstance().getReference();

        final FirebaseAuth mauth = FirebaseAuth.getInstance();
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View hj) {
                final int i = group.getCheckedRadioButtonId();
                RadioButton r = (RadioButton) v.findViewById(i);
                Toast.makeText(context, r.getText().toString(), Toast.LENGTH_SHORT).show();

                mdef.child("Test").child("python").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot i: dataSnapshot.getChildren())
                        {

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                mdef.child("Test Results").child("python").child("Event").child(mauth.getCurrentUser()
                        .getUid()).child((position+1)+"").setValue(r.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(context, "Submitted Successfully!!", Toast.LENGTH_SHORT).show();
                        m.setVisibility(View.INVISIBLE);
                        m.setEnabled(false);

                    }
                });
            }
        });

        container.addView(v);

        return v;

    }
}
