package com.dluis.platzigram.login.repository;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

public interface LoginRepository {

    void singIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth);
}
