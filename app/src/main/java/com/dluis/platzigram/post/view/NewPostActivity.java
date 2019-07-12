package com.dluis.platzigram.post.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dluis.platzigram.PlatzigramApplication;
import com.dluis.platzigram.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class NewPostActivity extends AppCompatActivity {

    private ImageView imgPhoto;
    private AppCompatButton btnCreatePost;
    private String photoPath;
    private PlatzigramApplication app;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        imgPhoto = (ImageView) findViewById(R.id.imgPhoto);

        app = (PlatzigramApplication) getApplicationContext();

        storageReference = app.getStorageReference();

        btnCreatePost = (AppCompatButton) findViewById(R.id.brnCreatePost);

        if (getIntent().getExtras() != null) {
            photoPath = getIntent().getExtras().getString("PHOTO_PATH_TEMP");
            showPhoto();
        }

        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPhoto();
            }
        });

    }

    private void uploadPhoto() {
        imgPhoto.setDrawingCacheEnabled(true);
        imgPhoto.buildDrawingCache();

        Bitmap bitmap = imgPhoto.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] photoByte = baos.toByteArray();

        int tamano = photoPath.length();

        String photoName = photoPath.substring(photoPath.lastIndexOf("/") + 1, tamano);

        StorageReference photoReference = storageReference.child("postImages/" + photoName);

        UploadTask updloadTask = photoReference.putBytes(photoByte);

        updloadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                FirebaseCrash.report(e);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri uriPhoto = taskSnapshot.getUploadSessionUri();

                String photoURL = uriPhoto.toString();

                System.out.println("URL de la foto -->> " + photoURL);

                finish();
            }
        });


    }

    private void showPhoto() {
        Picasso.get().load(photoPath).into(imgPhoto);
    }
}
