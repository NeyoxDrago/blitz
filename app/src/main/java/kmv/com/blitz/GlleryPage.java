package kmv.com.blitz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlleryPage extends AppCompatActivity {

    private TabLayout gallerytab;
    private ViewPager galleryviewpager;
    private FirebaseAuth mauth;
    private StorageReference mstorage;
    private DatabaseReference mdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gllery_page);

        gallerytab = (TabLayout) findViewById(R.id.gallerytab);
        galleryviewpager = (ViewPager) findViewById(R.id.GslleryViewPager);
        setupViewPager(galleryviewpager);

        mauth = FirebaseAuth.getInstance();
        mstorage = FirebaseStorage.getInstance().getReference();
        mdatabase = FirebaseDatabase.getInstance().getReference();
        gallerytab.setupWithViewPager(galleryviewpager);


    }

    public void setupViewPager(ViewPager upViewPager) {

        ViewPagerAdapter pager = new ViewPagerAdapter(getSupportFragmentManager());
        pager.add(new EventFragment() ,"Events");
        pager.add(new GalleryFragment() , "Gallery");
        galleryviewpager.setAdapter(pager);
    }


        class ViewPagerAdapter extends FragmentPagerAdapter{

        public final List<Fragment> mfragmentlist = new ArrayList<>();
        public final List<String> mtitlelist = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mfragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return mtitlelist.size();
        }

        public void add(Fragment eventFragment, String events) {
            mfragmentlist.add(eventFragment);
            mtitlelist.add(events);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mtitlelist.get(position);
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(mauth.getCurrentUser().getUid().equals("Wp2cZEjgSffjbbODM656TSBJcgz1") || mauth.getCurrentUser().getUid().equals("XyAADyFJvnTBUHTDjioSlSXsg4t2"))
        {
            getMenuInflater().inflate(R.menu.menu2 , menu);
            return true;
        }
        return false;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.addtogallery :
                CropImage.activity()
                        .setAutoZoomEnabled(true)
                        .setMaxZoom(4)
                        .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                        .start(GlleryPage.this);
        }
        return true;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                Uri resultUri = result.getUri();
                final StorageReference sto = mstorage.child("gallery").child(resultUri.getLastPathSegment());
                sto.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if(task.isSuccessful())
                        {
                            sto.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    mdatabase.child("gallery").push().setValue(uri.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful())
                                            {
                                                Toast.makeText(GlleryPage.this, " File Uploaded Successfully !!!! ", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });


                                }
                            });
                        }
                    }
                });
            }
        }
    }
}

