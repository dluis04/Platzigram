package com.dluis.platzigram.post.view;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.dluis.platzigram.PlatzigramApplication;
import com.dluis.platzigram.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class PictureDetailActivity extends AppCompatActivity {

    private ImageView imageHeader;
    private PlatzigramApplication app;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);


        app = (PlatzigramApplication) getApplicationContext();

        storageReference = app.getStorageReference();

        imageHeader = (ImageView) findViewById(R.id.imageheader);

        showToolbar("", true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Fade());
        }

        showData();

    }

    private void showData() {
        String namePhoto = "JPEG_20190708_05-10-09_746719906603685570.jpg";
        storageReference.child("postImages/" + namePhoto)
                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri.toString()).into(imageHeader); //traemos imagenes de internet

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("Error en el metodo showData " + e);
                FirebaseCrash.report(e);
            }
        });
    }

    public void showToolbar(String tittle, boolean upButton) {
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar); /*soporte para verse bien en versiones anteriores al loolipop*/
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);


        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);

    }
}
