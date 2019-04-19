package com.example.dip.lsl;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class uploadimages extends AppCompatActivity {

    String userName,descrip,formattedDate,formattedTime;
    private Uri uriprofileimage;
    StorageReference ImageRef;
    String ImageUrl;
    private final int CHOOSE_IMAGE = 101;
    DatabaseReference image_db;
    EditText description;
    ImageView iv;
    String uploadId, category,dept;
    FirebaseAuth mAuth;
    Spinner spinner,spinner2;
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);
    TextView touch;
    EditText editt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimages);


        touch = findViewById(R.id.touch);
        editt = findViewById(R.id.editt);
        mAuth = FirebaseAuth.getInstance();

        touch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();
            }
        });
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                uploadImageAndDescriptionToFirebase();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriprofileimage = data.getData();
           // iv.setImageURI(uriprofileimage);
        }
    }

    private void showImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), CHOOSE_IMAGE);
    }


    private void uploadImageAndDescriptionToFirebase() {

        image_db = FirebaseDatabase.getInstance().getReference().child("Days");
        // db2 = FirebaseDatabase.getInstance().getReference().child("uploads2");
        if (uriprofileimage != null) {

            ImageRef = FirebaseStorage.getInstance().getReference().child("uploads/" +
                    "/"+ System.currentTimeMillis() + "." + getFileExtension(uriprofileimage));

            ImageRef.putFile(uriprofileimage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(uploadimages.this, "Done", Toast.LENGTH_LONG).show();
                            ImageUrl = taskSnapshot.getDownloadUrl().toString();
                            // Upload upload = new Upload(descrip,ImageUrl,uploadId,formattedDate , userName);
                            upload up = new upload();
                            up.setUrl(ImageUrl);

                            up.setKey(editt.getText().toString());

                            image_db.child("" + System.currentTimeMillis()).setValue(up);

                           // finish();
                            //startActivity(new Intent(problemSubmissionPage.this,problemSubmissionPage.class));
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(uploadimages.this, "failed " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                        }

                    });
        }
    }
    
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}
