package kmv.com.blitz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignInPage extends AppCompatActivity {

    private TextInputLayout email , password;
    private Button signin;
    private TextView newuser;
    private FirebaseAuth mauth;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);


        mauth = FirebaseAuth.getInstance();

        email = (TextInputLayout) findViewById(R.id.signinEmailID);
        password= (TextInputLayout) findViewById(R.id.signinPassword);

        signin = (Button) findViewById(R.id.signinmebaby);
        newuser = (TextView) findViewById(R.id.newuser);

        progress = new ProgressDialog(this);
        progress.setMessage("Loading");

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(SignInPage.this , LOginActivity.class);
                startActivity(a);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress.show();
                String Email = email.getEditText().getText().toString().trim();
                String Password = password.getEditText().getText().toString().trim();

                mauth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            progress.dismiss();
                            Intent a =  new Intent(SignInPage.this , MainActivity.class);
                            startActivity(a);
                        }
                        else
                        {
                            Toast.makeText(SignInPage.this, task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mauth.getCurrentUser() != null)
        {
            Intent a =  new Intent(SignInPage.this , MainActivity.class);
            startActivity(a);
        }

    }
}
