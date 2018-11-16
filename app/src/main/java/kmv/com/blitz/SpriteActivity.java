package kmv.com.blitz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SpriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MySurface(getApplicationContext()));
    }
}
