package com.dluis.platzigram;

import android.app.Application;

import androidx.annotation.NonNull;

import com.facebook.FacebookSdk;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class PlatzigramApplication extends Application {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseStorage firebaseStorage;

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseCrash.log("Inicializando variables PlatzigramApplication");

        FacebookSdk.sdkInitialize(getApplicationContext());


        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = (new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if (firebaseUser != null) {
                    System.out.println("Usuario Logeado");
                } else {
                    System.out.println("Usuario No Logeado");
                }

            }
        });

        firebaseStorage = FirebaseStorage.getInstance();


    }


    public StorageReference getStorageReference() {
        return firebaseStorage.getReference();
    }


}
