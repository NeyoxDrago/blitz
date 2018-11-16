package kmv.com.blitz;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LOginActivity extends AppCompatActivity {

    private TextInputLayout email , password, rollnumber;
    private ImageView id;
    private Button submit ;
    private Spinner course;
    private FirebaseAuth mauth;
    private DatabaseReference mdef;
    private StorageReference mstorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mauth = FirebaseAuth.getInstance();
        mdef = FirebaseDatabase.getInstance().getReference();
        mstorage = FirebaseStorage.getInstance().getReference();

        email = (TextInputLayout) findViewById(R.id.loginemail);
        password = (TextInputLayout) findViewById(R.id.loginpassword);
        rollnumber = (TextInputLayout) findViewById(R.id.loginrollnumber);
        id = (ImageView) findViewById(R.id.addidimage);
        submit = (Button) findViewById(R.id.loginsubmit);
        course = (Spinner) findViewById(R.id.logincourse);

        ArrayAdapter a = ArrayAdapter.createFromResource(this, R.array.courses, android.R.layout.simple_spinner_item);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        course.setAdapter(a);

        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                        .start(LOginActivity.this);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Email = email.getEditText().getText().toString().trim();
                final String Password = password.getEditText().getText().toString().trim();
                final String Rollnumber = rollnumber.getEditText().getText().toString().trim();
                final String Course = course.getSelectedItem().toString();

                mauth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            mdef.child("Users").child("temporary").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    String url = dataSnapshot.getValue().toString().trim();

                                    Map<String, Object> map = new HashMap<>();
                                    map.put("Email", Email);
                                    map.put("Password", Password);
                                    map.put("RollNumber", Rollnumber);
                                    map.put("Course", Course);
                                    map.put("id", url);
                                    map.put("username", "Anonymous");
                                    map.put("photo", "");

                                    mdef.child("Users").child(mauth.getCurrentUser().getUid()).setValue(map);

                                    mdef.child("Users").child("temporary").removeValue();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });




                        } else {
                            Toast.makeText(LOginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK)
            {
                Uri uri = result.getUri();
                RequestOptions r = new RequestOptions().placeholder(R.drawable.addimage);
                Glide.with(this).load(uri).apply(r).into(id);
                id.setBackground(null);

                final StorageReference filepath = mstorage.child("ID").child(uri.getLastPathSegment());
                filepath.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if(task.isSuccessful())
                        {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {


                                    mdef.child("Users").child("temporary").setValue(uri.toString());


                                }
                            });
                        }
                    }
                });


            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
            }

        }
    }
}

