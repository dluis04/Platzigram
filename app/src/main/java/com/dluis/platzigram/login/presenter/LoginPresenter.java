package com.dluis.platzigram.login.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

public interface LoginPresenter {

    void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth); //Interactua con el interactor

    void loginSuccess();

    void loginError(String mensaje);
}
