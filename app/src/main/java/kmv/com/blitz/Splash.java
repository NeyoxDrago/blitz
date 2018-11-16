package kmv.com.blitz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.opencv.android.OpenCVLoader;

import su.levenetc.android.textsurface.TextSurface;

public class Splash extends AppCompatActivity {

    private TextSurface textsurface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new MySurface(getApplicationContext()));
        setContentView(R.layout.activity_sprite);


        if(OpenCVLoader.initDebug())
        {
            Toast.makeText(this, "OpenCv Successfully Installed..", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Error Occurred", Toast.LENGTH_SHORT).show();
        }

        textsurface  = (TextSurface) findViewById(R.id.textsurface);
       textsurface.postDelayed(new Runnable() {
           @Override
           public void run() {
          
               show();
           }
       } , 500);

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {

               Intent a = new Intent(Splash.this , homepage.class);
               startActivity(a);
           }
       } ,11000);
    }

    private void show() {

        textsurface.reset();
        try_surface.play(textsurface);

    }
}
