package kmv.com.blitz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    private ImageView addImage;
    private TextInputLayout articletext;
    private Button Submit;
    private StorageReference mstorage;
    private ProgressDialog dialog;
    private DatabaseReference mdef , mdef2;
    private Uri resultUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Uploading");

        addImage  =(ImageView) findViewById(R.id.addimagebutton);
        articletext = (TextInputLayout) findViewById(R.id.addarticletext);
        Submit = (Button) findViewById(R.id.submitarticle);

        mstorage = FirebaseStorage.getInstance().getReference();
        mdef = FirebaseDatabase.getInstance().getReference().child("articles");
        mdef2 = FirebaseDatabase.getInstance().getReference();




        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                        .start(AddActivity.this);


            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mdef.child("temporary").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String url = dataSnapshot.child("imageurl").getValue().toString();

                        String text = articletext.getEditText().getText().toString().trim();

                        Map<String,Object> a = new HashMap<>();
                        a.put("imageurl", url);
                        a.put("username","Prashant");
                        a.put("text",text);

                        mdef.push().setValue(a);

                        mdef.child("temporary").removeValue();
                        mdef2.child("gallery").push().setValue(url);
                        Intent in = new Intent(AddActivity.this , MainActivity.class);
                        startActivity(in);



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

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

                    resultUri = result.getUri();
                    RequestOptions r = new RequestOptions().placeholder(R.drawable.ic_launcher_foreground);
                    Glide.with(AddActivity.this).load(resultUri).apply(r).into(addImage);

                    dialog.show();

                    final StorageReference filepath = mstorage.child("article Image").child(resultUri.getLastPathSegment());
                    filepath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                            if(task.isSuccessful())
                            {
                                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        final String url = uri.toString();

                                        Map<String,Object> map = new HashMap<>();
                                        map.put("imageurl" , url);
                                        map.put("username", "prashant");
                                        map.put("text" , "No text");
                                        mdef.child("temporary").setValue(map);
                                        dialog.dismiss();
                                    }
                                });
                            }
                        }
                    });

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                    Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
                }
            }
    }
}
