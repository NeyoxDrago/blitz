package kmv.com.blitz;

import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import ernestoyaquello.com.verticalstepperform.*;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class stepperActivity extends AppCompatActivity implements VerticalStepperForm{

    private VerticalStepperFormLayout verticalStepperForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepper);

        String[] mySteps = {"Introduction" , "History"};
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);

        // Finding the view
        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.vertical_stepper);

        // Setting up and initializing the form
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm ,mySteps ,this ,this)
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .displayBottomNavigation(false) // It is true by default, so in this case this line is not necessary
                .init();

    }

    private View createIntroduction()
    {
        TextView  a = new TextView(this);
        a.setText("hello Everyone , this is the introduction part ,hello Everyone , this is the introduction part" +
                "hello Everyone , this is the introduction part" +
                "hello Everyone , this is the introduction parthello Everyone , this is the introduction part" +
                "hello Everyone , this is the introduction part" +
                "hello Everyone , this is the introduction part" +
                "hello Everyone , this is the introduction part" +
                "hello Everyone , this is the introduction part" +
                "hello Everyone , this is the introduction part" +
                "hello Everyone , this is the introduction part" +
                "vhello Everyone , this is the introduction part" +
                "" +
                "");
        a.setVerticalScrollBarEnabled(true);
        return a;
    }

    private View createHistory()
    {
        TextView  a = new TextView(this);
        a.setText("hello Everyone , this is the History part");
        return a;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View createStepContentView(int stepNumber) {
        View v = null;
        switch(stepNumber)
        {
            case 0:
                v = createIntroduction();
                break;
            case 1:
                v = createHistory();
                break;
        }
        return v;
    }

    @Override
    public void onStepOpening(int stepNumber) {

    }

    @Override
    public void sendData() {

    }
}
