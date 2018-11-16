package kmv.com.blitz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.github.igla.ferriswheel.FerrisWheelView;
import shortbread.Shortcut;

@Shortcut(id = "Ferriswheel" ,
        icon = R.drawable.arrow_up ,
        rank = 1,
        shortLabel = "Ferris Wheel" ,
        activity = FerrisWheel.class)
public class FerrisWheel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainsplash);

        FerrisWheelView f = (FerrisWheelView) findViewById(R.id.ferriswheel);
        f.startAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent b= new Intent(FerrisWheel.this,SignInPage.class);
                FerrisWheel.this.startActivity(b);
                FerrisWheel.this.finish();

            }
        } , 4000);

    }
}
