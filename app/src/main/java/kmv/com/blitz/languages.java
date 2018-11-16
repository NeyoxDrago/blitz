package kmv.com.blitz;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import quatja.com.vorolay.VoronoiView;

public class languages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        final VoronoiView voro  = (VoronoiView)  findViewById(R.id.voronoilanguages);

        LayoutInflater l = getLayoutInflater();

        String[] array = this.getResources().getStringArray(R.array.languages);

        int f[] = new int[]{ Color.BLUE  , Color.RED, Color.GREEN , Color.YELLOW , Color.MAGENTA , Color.CYAN , };

        Random a = new Random();

        for(int i =0;i<5;i++)
        {
            View v = l.inflate(R.layout.voro,null,false);
            final TextView t = (TextView) v.findViewById(R.id.vorotext);


            View layout   = v.findViewById(R.id.layout);
            layout.setBackgroundColor(Color.argb(255 , a.nextInt(255) ,a.nextInt(255),a.nextInt(255) ));
            t.setText(array[i]);
            voro.addView(v);
        }

        voro.setOnRegionClickListener(new VoronoiView.OnRegionClickListener() {
            @Override
            public void onClick(View view, int i) {

                Toast.makeText(view.getContext(), i+"", Toast.LENGTH_SHORT).show();

                Intent a = new Intent(languages.this , TestActivity.class);
                startActivity(a);

            }
        });



    }
}
